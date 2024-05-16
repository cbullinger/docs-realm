import Realm, { BSON, ObjectSchema } from "realm";
import { Item, Project } from "./models/rql-data-models.ts";

describe("Test Item and Project RQL Models", () => {
  let realm: Realm;
  const config = { schema: [Project, Item] };

  beforeEach(async () => {
    realm = await Realm.open(config);
  });

  afterEach(() => {
    // After each test, delete the objects and close the realm
    if (realm && !realm.isClosed) {
      realm.write(() => {
        realm.deleteAll();
      });
      realm.close();
      expect(realm.isClosed).toBe(true);
    }
  });

  afterAll(() => {
    Realm.deleteFile(config);
  });

  test("Can open realm with config", async () => {
    expect(realm.isClosed).toBe(false);
  });

  test("Can create object of Item type", () => {
    const itemId = new BSON.ObjectId();
    realm.write(() => {
      realm.create("Item", {
        _id: itemId,
        name: "get coffee",
      });
    });
    const coffeeItem = realm.objects("Item")[0];
    expect(coffeeItem._id).toEqual(itemId);
    expect(coffeeItem.name).toBe("get coffee");
    expect(coffeeItem.isComplete).toBe(false);
  });
  test("Can create object of Project type", () => {
    const projectId = new BSON.ObjectId();
    realm.write(() => {
      const teaItem = realm.create("Item", {
        _id: new BSON.ObjectId(),
        name: "get tea",
      });
      realm.create(Project, {
        _id: projectId,
        name: "beverages",
        items: [teaItem],
      });
    });
    const bevProject = realm.objects(Project)[0];
    expect(bevProject._id).toEqual(projectId);
    expect(bevProject.name).toBe("beverages");
    expect(bevProject.items[0].name).toBe("get tea");
  });
});
