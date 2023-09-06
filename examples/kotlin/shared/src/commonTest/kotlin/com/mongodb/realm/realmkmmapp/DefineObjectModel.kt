package com.mongodb.realm.realmkmmapp

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.platform.runBlocking
import org.mongodb.kbson.ObjectId
import kotlin.test.Test


// :replace-start: {
//    "terms": {
//       "ExampleModel_": "",
//       "ExamplePropertyAnnotations_": "",
//       "RealmDictionary_": "",
//       "RealmSet_": ""
//   }
// }
class DefineObjectModel {

    @Test
    fun defineExamplePropertyAnnotationsTest() {
        runBlocking {
            val config = RealmConfiguration.create(setOf(ExamplePropertyAnnotations_User::class, ExamplePropertyAnnotations_Employee::class))
            val realm = Realm.open(config)

            val userObjectId = ObjectId()
            val employeeObjectId = ObjectId()

            realm.write {
                copyToRealm(ExamplePropertyAnnotations_User().apply {
                    _id = userObjectId
                    name = "John Smith"
                    temporaryId = 1234
                    office = "HQ"
                    personalBio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                })
                copyToRealm(ExamplePropertyAnnotations_Employee().apply {
                    _id = employeeObjectId
                    name = "Jane Doe"
                    department = "Engineering"
                    userField = copyToRealm(ExamplePropertyAnnotations_User().apply {
                        _id = ObjectId()
                        name = "Jane Smith"
                        temporaryId = 1234
                        office = "HQ"
                        personalBio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    })
                })

                val user = query<ExamplePropertyAnnotations_User>().find().first()
                val employee = query<ExamplePropertyAnnotations_Employee>().find().first()
                println("User: ${user?.name}, ${user?.temporaryId}, ${user?.office}, ${user?.personalBio}")
                val queryPersistedName =
                    query<ExamplePropertyAnnotations_Employee>("name = $0", "Jane Doe").find()
                println(queryPersistedName)

                val primaryKey =
                    println(ExamplePropertyAnnotations_Employee::class.qualifiedName)
                println(ExamplePropertyAnnotations_User::class.simpleName)
                println()


                val ftsSearch = realm.query<ExamplePropertyAnnotations_User>("personalBio TEXT $0", "ipsum").find()
                println(ftsSearch)
                //assertEquals(1, ftsSearch.size)

                deleteAll()
            }
            realm.close()
            Realm.deleteRealm(config)
        }
    }
}
// :replace-end: