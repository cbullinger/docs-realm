import Realm, { BSON, ObjectSchema } from "realm";
import { Item, Project } from "./models/rql-data-models.ts";

describe("Test Item and Project RQL Models", () => {
  let realm: Realm;
  const path = "testing.realm";
  beforeEach(async () => {
    realm = await Realm.open({
      schema: [Project, Item],
      deleteRealmIfMigrationNeeded: true,
      path: path,
    });
  });

  afterEach(() => {
    // After the test, delete the objects and close the realm
    if (realm && !realm.isClosed) {
      realm.write(() => {
        realm.deleteAll();
      });
      realm.close();
    }
  });

  afterAll(() => {
    Realm.deleteFile({ path });
  });

  test("open realm with config", async () => {
    expect(realm.isClosed).toBe(false);
  });
  test("Can create object of Item type", () => {
    realm.write(() => {
      realm.create("Item", {
        id: new Realm.BSON.ObjectId(),
        name: "get coffee",
      });
    });
    const coffeeItem = realm.objects("Item")[0];
    expect(coffeeItem.id instanceof Realm.BSON.ObjectId).toBe(true);
    expect(coffeeItem.name).toBe("get coffee");
    expect(coffeeItem.isComplete).toBe(false);
  });
  test("Can create object of Project type", () => {
    realm.write(() => {
      const teaItem = realm.create("Item", {
        id: new Realm.BSON.ObjectId(),
        name: "get tea",
      });
      realm.create("Project", {
        id: new Realm.BSON.ObjectId(),
        name: "beverages",
        items: [teaItem],
      });
    });
    const bevProject = realm.objects("Project")[0];
    expect(bevProject.id instanceof Realm.BSON.ObjectId).toBe(true);
    expect(bevProject.name).toBe("beverages");
    expect(bevProject.items[0].name).toBe("get tea");
  });
});
