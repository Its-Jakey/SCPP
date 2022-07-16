# Syntax

## Literals

Numeric literals can be written in the following ways:

    1234
    12.24
    .1234
    -1234
    -12.24
    0x1234 //hexadecimal
    0b0111 //binary

String literals can be written in the following way:

    "Hello, world!"

## Operators

Values can be combined using the following operators:

| Operator | Description
| :------: | :-
|    `+`   | Addition
|    `-`   | Subtraction
|    `*`   | Multiplication
|    `/`   | Division
|   `<<`   | Left shift
|   `>>`   | Right shift
|    `\|`   | Bitwise OR
|    `&`   | Bitwise AND
|    `^`   | Bitwise XOR
|    `%`   | Modulo
|   `\|\|`   | Logical OR
|   `&&`   | Logical AND
|    `>`   | Greater than
|    `<`   | Less than
|   `==`   | Equal to
|   `!=`   | Not equal to
|   `>=`   | Greater than or equal to
|   `<=`   | Less than or equal to
<!--- |    `!`   | Not -->

## Defining Variables

There are 2 types of variables, global and local. A variable is global when it is defined outside a function, otherwise it is local.
Variables can be defined by using the 'var' keyword like

    var x = value

or an array with values like, this also will set x to the address that is the start of the array

    var array = {a, b, c, d...};

or an empty array with

    var array = malloc(arraySize);

Or create a matrix like

    var matrix = {{1, 2, 3}, {a, b, c}};

Or an empty matrix with

    var matrix = {malloc(4), malloc(4)}; //A 2x4 matrix, I may create a library for matrixs in the future 

You can get a value from an array like

    var array = {1, 2, 3};
    println(array[1]); //2


Variables can be later reassigned to using the '=' operator like

    x = value

You can also use modifiers to modify the value of a variable like

    x += value
    x -= value

There are two special modifiers:

    x ++
    x --

Constants can also be defined with '#define'

    #define MY_CONSTANT 21
    var x = MY_CONSTANT;

## Functions

Functions must be declared when in a namespace and not in a function definition.
Functions can be defined with

    func funcName(arguments...) {yourCodeHere...}
Functions arguments a formatted like

    arg1, arg2, arg3
Functions can be called with

    funcName(args...);

Functions can also return variables with the 'return' keyword

    return value;
    var x = funcName();

## Namespaces

Namespaces are like separate packages for functions and global variables.
Namespaces can be defined with the 'namespace' keyword like

    namespace myNamespace {yourCodeHere...}

When you define a function or global variable with the 'public' keyword prefix, you can access it from another namespace like

    namespace otherNamespace {
        public var globalVariable = value;
        public func function(arguments...) {yourCodeHere...}
    }

    otherNamespace::function(args...);
or

    var x = otherNamespace::globalVariable;

You can also make a duplicate of a namespace with the 'is' keyword

    namespace space {code...}
    namespace newNamespace is space //Creates a duplicate of 'space' named 'newNamespace'

## If and While loops

If and while are both statements that take a condition as input and will either run your code once or repeat your code until the input condition is less than 1.

If statements are used like

    if (condition) {code...}

While statements are used like

    while (condition) {code...}

## For loops

A for loop will loop your code until a variable is not smaller than the target variable, the variable with increment by 1 by default, but can be changed by adding 'by \[increment]'.
For statements can be either used like

    for (i from start to end) {code...}
Or like

    for (i from start to end by increment)

## Switch statements

!!! Note: This is not yet implemented

Switch statements can be used like

    switch (value) {
        case value -> {
            code...
        };
        case value -> {
            code...
        };
        default -> {
            code...
        };
    };

## #include

\#include takes a filename or a builtin file and adds all the namespaces from that file to your current file
\#include can be used either like

    #include "[filepath]"

or like

    #include <[library name]>

## Comments

comments are used to place text in your code. There are 2 types of comments, block comments and line comments.

    //This is a line comment!
    /*This is a block comment!
    I can be multiple lines!*/
