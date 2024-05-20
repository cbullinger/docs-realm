.. code-block:: typescript

     // Evaluates if there is a dictionary key with the name 'foo'
     "ANY dict.@keys == $0",
     "foo"

     // Evaluates if there is a dictionary key with key 'foo' and value 'bar
     "dict['foo'] == $0",
     "bar"

     // Evaluates if there is greater than one key-value pair in the dictionary
     "dict.@count > $0",
     1

     // Evaluates if dictionary has property of type 'string'
     "ANY dict.@type == 'string'"

     // Evaluates if all the dictionary's values are of type 'int'
     "ALL dict.@type == 'int'"

     // Evaluates if dictionary does not have any values of type 'int'
     "NONE dict.@type == 'int'"

     // ANY is implied.
     "dict.@type == 'string'"
