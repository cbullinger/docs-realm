package com.mongodb.realm.realmkmmapp

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmDictionaryOf
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.*
import io.realm.kotlin.types.annotations.*
import org.mongodb.kbson.ObjectId

// :replace-start: {
//    "terms": {
//       "ExampleModel_": "",
//       "ExamplePropertyAnnotations_": "",
//       "RealmDictionary_": "",
//       "RealmSet_": ""
//   }
// }

// Relationships page

// Property Annotations page
// :snippet-start: example-persisted-class
@PersistedName(name = "User") // Remapped class name
class ExamplePropertyAnnotations_Employee : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var department: String = ""
    var userField: ExamplePropertyAnnotations_User? = null
}
// :snippet-end:

// :snippet-start: example-property-annotations
class ExamplePropertyAnnotations_User : RealmObject {
    // :snippet-start: example-primary-key
    @PrimaryKey // Primary key property
    @Index
    var _id: ObjectId = ObjectId()
    // :snippet-end:
    // :snippet-start: example-index
    @Index // Indexed property
    var name: String = ""
    // :snippet-end:
    // :snippet-start: example-ignore
    @Ignore // Ignored property
    var temporaryId: Int = 0
    // :snippet-end:
    // :snippet-start: example-persisted-property
    @PersistedName(name = "office_location") // Remapped property
    var office: String? = null
    // :snippet-end:
    // :snippet-start: example-full-text
    @FullText // Full-text search indexed property
    var personalBio: String = ""
    // :snippet-end:
}
// :snippet-end:



// Define Realm Object Model page

// :snippet-start: define-realm-object
// Note the empty constructor and that the
// class inherits from `RealmObject`
class ExampleModel_Frog : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var age: Int = 0
}
// :snippet-end:

// :snippet-start: define-to-one-relationship-property
class ExampleModel_FrogOwner : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var address: String = ""
    // Property of RealmObject type (MUST be null)
    var frog: ExampleModel_Frog? = null
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: EmbeddedRealmObject_Forest? = null
}
// :snippet-end:

// :snippet-start: define-to-many-relationship-property
class ExampleModel_Pond : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Set of RealmObject type (CANNOT be null)
    var frogsThatLiveHere: RealmSet<ExampleModel_Frog> = realmSetOf()
    // List of EmbeddedRealmObject type (CANNOT be null)
    var nearbyForests: RealmList<EmbeddedRealmObject_Forest> = realmListOf()
}
// :snippet-end:

// :snippet-start: define-inverse-property-parent
class ExampleModel_GrandpaFrog : RealmObject {
    var name: String = ""
    var children: RealmList<ExampleModel_PapaFrog> = realmListOf()
    var grandchildren: RealmSet<ExampleModel_Tadpole> = realmSetOf()
    var favoriteChildByAge: RealmDictionary<ExampleModel_PapaFrog?> = realmDictionaryOf()
}
// :snippet-end:
// :snippet-start: define-inverse-property-children
class ExampleModel_PapaFrog : EmbeddedRealmObject {
    var name: String = ""
    val parent: ExampleModel_GrandpaFrog
        by backlinks(ExampleModel_GrandpaFrog::children) // must be val
}

class ExampleModel_Tadpole : RealmObject {
    var name: String = ""
    val grandparent: RealmResults<ExampleModel_GrandpaFrog>
            by backlinks(ExampleModel_GrandpaFrog::grandchildren) // must be val
}

// :snippet-end:

// :snippet-start: define-embedded-object
// Inherits from `EmbeddedRealmObject`
// Cannot have a primary key
class EmbeddedRealmObject_Forest : EmbeddedRealmObject {
    var name: String = ""
}
// :snippet-end:
// :snippet-start: embed-defined-object
// Embedded objects MUST be referenced by a parent
// object. They cannot exist as independent objects.
class EmbeddedRealmObject_ParentFrog : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: EmbeddedRealmObject_Forest? = null
    // List of EmbeddedRealmObject type (CANNOT be null)
    var favoriteForests: RealmList<EmbeddedRealmObject_Forest> = realmListOf()
}
// :snippet-end:

// Used in AsymmetricSyncTest.kt
// :snippet-start: define-asymmetric-model
// Inherits from `AsymmetricRealmObject`
class WeatherSensor : AsymmetricRealmObject {
    @PersistedName("_id")
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var deviceId: String = ""
    var temperatureInFarenheit: Float = 0.0F
    var barometricPressureInHg: Float = 0.0F
    var windSpeedInMph: Int = 0
}
// :snippet-end:

// :snippet-start: define-a-realm-list
// RealmList<E> can be any supported primitive or BSON type,
// a RealmObject, or an EmbeddedRealmObject
class RealmList_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // List of RealmObject type (CANNOT be nullable)
    var favoritePonds: RealmList<RealmList_Pond> = realmListOf()
    // List of String values (can be nullable)
    var favoriteWeather: RealmList<String?> = realmListOf()
}

class RealmList_Pond : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
// :snippet-end:


// :snippet-start: define-a-realm-set
// RealmSet<E> can be any supported primitive or BSON type
// or a RealmObject
class RealmSet_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Set of RealmObject type (CANNOT be nullable)
    var favoriteSnacks: RealmSet<RealmSet_Snack> = realmSetOf()
    // Set of String values (can be nullable)
    var favoriteWeather: RealmSet<String?> = realmSetOf()
}

class RealmSet_Snack : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
// :snippet-end:

// :snippet-start: define-realm-dictionary-property
// RealmDictionary<K, V> can be any supported primitive or BSON types,
// a RealmObject, or an EmbeddedRealmObject
class RealmDictionary_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Dictionary of RealmObject type (value MUST be nullable)
    var favoriteFriendsByForest: RealmDictionary<RealmDictionary_Friend?> = realmDictionaryOf()
    // Dictionary of String values (value can be nullable)
    var favoritePondsByForest: RealmDictionary<String?> = realmDictionaryOf()
}

class RealmDictionary_Friend : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
// :snippet-end:

// :replace-end: