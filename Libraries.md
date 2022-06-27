# Builtin Libraries

Builtin Libraries can be included in your code with

    #include <[library]>

## \_\_builtin__

This is technically not a library as it is available to all programs.

> **println(string1, string2, string3..);** Prints the input strings to the console separated by a space, then starts a new line
> 
> **print(string1, string2, string3..);** Prints the input strings to the console separated by a space
> 
> **\_asm_(...);** Executes the given assembly code, then returns the value of the A register
> 
> **malloc(size);** Allocates memory of size cells and returns the first address that it allocated
> 
> **exit();** Exits the program
> 
> **concat(args...)** Returns all the arguments combined into a string

## cloud

The cloud library is used to interact with cloud variables

> **setVar(varName, value);** Sets the cloud variable \(0 to 9) to the specified value
>
> **getVar(varName);** Returns the value of the cloud variable \(0 to 9);
>
> **encode(string);** Returns the input string encoded to numbers
>
> **decode(string);** Returns the decoded form of the number string

## graphics

The graphics library is used to draw vector graphics on the screen, including text

> **putPixel(x, y);** Sets the pixel on screen at x y to the current color
>
> **drawLine(x0, y0, x1, y1);** Draws a line on screen from x0 y0 to x1 y1
>
> **fillRect(x, y, width, height);** Draws a rectangle width*heigh on screen at x y
>
> **setColor(color);** Sets the current color to color
>
> **setStrokeWidth(strokeWidth);** Sets the size of the pen to strokeWidth
>
> **clear();** Clears the graphics buffer
>
> **drawString(string);** Draws the string on screen at the current x and y
>
> **drawStringLine(string);** Draws the string onscreen at the current x amd y then starts a new line
>
> **createColor(r, g, b);** Returns the color of the combined R G and B values
>
> **flip();** Draws the graphics buffer on screen, then clears the buffer
>
> **newLine();** Moves the text pointer to the next line
>
> **goto(x, y);** Moves the text pointer to x y

## input

The input library allows you to access user input like the current mouse position

> **mouseX();** Returns the current mouse X coordinate
>
> **mouseY();** Returns the current mouse Y coordinate
>
> **isMouseDown();** Returns 1 if the mouse is down, otherwise returns 0
>
> **isKeyPressed(key);** Returns 1 if the key is pressed on the keyboard, otherwise returns 0
>
> **ask(message);** Prompts the user for input

## math

The math library allows for loose geometric calculations and others

> **round(x, places);** Returns x rounded to the specified decimal places
> 
> **floor(x, places);** Returns x floored to the specified decimal places
> 
> **ceil(x, places);** Returns the ceiling of x to the specified decimal places
>
> **sin(x (as radians));** Returns the sin of x as radians
>
> **cos(x (as radians));** Returns the cos of x as radians
>
> **sqrt(x);** Returns the square root of x
>
> **atan2(x, y);** Returns the arc tangent of x and y in radians
>
> **negate(x);** Returns -x
>
> **min(a, b);** Returns the smallest out of a and b
>
> **max(a, b);** Returns the largest out of a and b
>
> **inRange(x, a, b);** Returns 1 if x is larger than a and smaller than b, otherwise returns 0
>
> **abs(x);** Returns the absolute of x

## strings

The strings library helps with editing strings

> **charAt(string, idx);** Returns character at idx of string
>
> **sizeOf(string);** Returns the length of string
>
> **contains(string, x);** Returns 1 if string contains x, otherwise returns 0
>
> **join(a, b);** Returns a concatenated with b
>
> **substring(string, start, end);** Returns a substring of string from start to end
>
> **repeat(string, times);** Returns string repeated times
>
> **indexOfChar(string, char);** Returns the first index of char in string

## time

The time library allows for timing operations to the nearest millisecond

> **sleep(millis);** Waits until the current runtime length is millis longer
> **getRuntimeMillis();** Returns the current length of the runtime in milliseconds
