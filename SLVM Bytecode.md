# Virtual Machine Specifications
The SLVM (Scratch Language Virtual Machine) can be used for a multitude of different languages, here it is being used to run SCPP code.

## Architecture

The core of the VM is a program queue. See [instructions](#instructions) for more information on its content.

The VM has a fixed amount of RAM cells. Each cell can hold a number or a arbitrary-length string.

By default, the VM has 65536 cells.

The VM has a stack that can be used to store stack frames.

The VM has a secondary queue used to store graphic operations.

The VM also has six registers: a,b and 1-4

A console that can be used to display messages.

## Instructions

Notation:

    x: value
    [x]: variable

| instruction                 | arguments | description
|-----------------------------| -------- |--------------------------------------------------------------------------------------------------------------------------------------------------------
| `ldi`                       | value    | sets the a register to the value                                                                                                                       
| `loadAtVar`                 | [var]    | sets the a register to the value of the variable [var]                                                                                                 
| `storeAtVar`                | [var]    | sets the variable [var] to the value of the a register                                                                                                 
| `jts`                       | value    | pushes the current instruction pointer to the stack and jumps to the value                                                                             
| `ret`                       |          | pops the top stack frame and jumps to the value                                                                                                        
| `addWithVar`                | [var]    | adds the value to the variable [var] to the a register                                                                                                 
| `subWithVar`                | [var]    | subtracts the value from the variable [var] to the a register                                                                                          
| `mulWithVar`                | [var]  | multiplies the value to the variable [var] to the a register                                                                                           
| `divWithVar`                | [var]  | divides the value from the variable [var] to the a register                                                                                            
| `bitwiseLsfWithVar`         | [var] | Leftshifts the A register by [var]                                                                                                                     
| `bitwiseRsfWithVar`         | [var] | Rightshifts the A regiser by [var]                                                                                                                     
| `bitwiseAndWithVar`         | [var] | Performs a bitwise and with the A regiser and [var]                                                                                                    
| `bitwiseOrWithVar`          | [var] | Performs a bitwise or with the A regiser and [var]                                                                                                     
| `modWithVar`                | [var]  | modulus the value from the variable [var] to the a register                                                                                            
| `print`                     |          | appends the value of the a register to the last line of the console                                                                                    
| `println`                   |          | appends the value of the a register to the last line of the console and starts a new line                                                              
| `jmp`                       | value    | jumps to the value                                                                                                                                     
| `jt`                        | value    | jumps to the value if the a register is greater than 0                                                                                                 
| `jf`                        | value    | jumps to the value if the a register is less than 1                                                                                                    
| `boolAndWithVar`            | [var] | ands the value to the variable [var] to the a register                                                                                                 
| `boolOrWithVar`             | [var] | ors the value to the variable [var] to the a register                                                                                                  
| `boolEqualsWithVar`         | [var] | equals the value to the variable [var] to the a register                                                                                               
| `largerThanOrEqualWithVar`  | [var] | equals the value to the variable [var] to the a register                                                                                               
| `smallerThanOrEqualWithVar` | [var] | equals the value to the variable [var] to the a register                                                                                               
| `boolNotEqualsWithVar`      | [var] | equals the value to the variable [var] to the a register                                                                                               
| `smallerThanWithVar`        | [var] | equals the value to the variable [var] to the a register                                                                                               
| `largerThanWithVar`         | [var] | equals the value to the variable [var] to the a register                                                                                               
| `putPixel`                  | [x], [y] | sets the pixel at x, y to the color set by `setColor`                                                                                                  
| `putLine`                   | [x1], [y1], [x2], [y2] | draws a line from x1, y1 to x2, y2 with the color set by `setColor`                                                                                    
| `putRect`                   | [x], [y], [w], [h] | draws a rectangle from x, y to x+w, y+h with the color set by `setColor`                                                                               
| `setColor`                  | [color] | sets the color of the next rect, line or pixel                                                                                                         
| `clg`                       | | clears the image buffer                                                                                                                                
| `done`                      | | ends the program                                                                                                                                       
| `malloc`                    | [size] | allocates memory and sets the a register to the address of the first cell                                                                                                                                       
| `round`                     | [1] [2] | rounds the value of [1] to [2] decimal places                                                                                                          
| `floor`                     | [1] [2] | floors the value of [1] to [2] decimal places                                                                                                          
| `ceil`                      | [1] [2] | ceils the value of [1] to [2] decimal places                                                                                                           
| `cos`                       | [1] | sets the a register to the cosine of [1] (rad)                                                                                                         
| `sin`                       | [1] | sets the a register to the sine of [1] (rad)                                                                                                           
| `sqrt`                      | [1] | sets the a register to the square root of [1]                                                                                                          
| `atan2`                     | [1], [2] | sets the a register to the arctangent of [1]/[2]                                                                                                       
| `mouseDown`                 | | sets the a register to 1 if the mouse is down, 0 otherwise                                                                                             
| `mouseX`                    | | sets the a register to the x coordinate of the mouse                                                                                                   
| `mouseY`                    | | sets the a register to the y coordinate of the mouse                                                                                                   
| `sleep`                     | [1] | sleeps for [1] milliseconds                                                                                                                            
| `drawText`                  | [text] | draws the text [text] at the current position                                                                                                          
| `loadAtVarWithOffset`       | [var], [offset] | sets the a register to the value of the variable [var] offset by [offset]                                                                              
| `storeAtVarWithOffset`      | [var], [offset] | sets the variable [var] offset by [offset] to the value of the a register                                                                              
| `isKeyPressed`              | [key] | sets the a register to 1 if the key [key] is pressed, 0 otherwise                                                                                      
| `createArray`               | [var], [size] | creates an array of size [size] and sets the variable [var] to the address of the array. Does nothing if a variable with the same name already exists. 
| `createColor`               | [r], [g], [b] | creates a color with the given RGB values and sets the a register to the address of the color                                                          
| `charAt`                    | [var], [index] | sets the a register to the character at index [index] in the string stored in [var]                                                                    
| `contains`                  | [text1], [text2] | sets the a register to 1 if [text2] is contained in [text1], 0 otherwise                                                                               
| `join`                      | [text] | sets the a register to (a register) + text                                                                                                              
| `setStrokeWidth`            | [width] | sets the stroke width to [width]                                                                                                                       
| `inc`                       | [var] | increments the variable [var] by 1                                                                                                                     
| `dec`                       | [var] | decrements the variable [var] by 1                                                                                                                     
| `arraySize`                 | [var] | sets the a register to the size of the array stored in [var]                                                                                           
| `graphicsFlip`              | | flips the graphics buffer                                                                                                                              
| `newLine`                   | | draws a new line                                                                                                                                       
| `ask`                       | [text] | displays the text [text] and waits for the user to enter a string. Sets the a register to the string entered.                                          
| `setCloudVar`               | [cloudVarID], [var] | sets the cloud variable [cloudVarID] to the value of the variable [var]                                                                                
| `getCloudVar`               | [cloudVarID] | sets the a register to the value of the cloud variable [cloudVarID]                                                                                    
| `indexOfChar`               | [string] [char] | sets the a register to the index of the first occurence of [char] in [string]                                                                          
| `goto`                      | [x], [y] | moves to graphics pointer to [x], [y]
| `imalloc`                   | size | allocates size cells of memory and sets the address of the first to the a register
| `getValueAtPointer`         | [var] | sets the A register to the value at memory address [var]
| `setValueAtPointer`         | [var] | sets the memory address at [var] to the A register
| `typeOf`                    | [var] | returns the type of [var] in string form (12: 'int', 6.2: 'float', 'sup': 'string')
| `runtimeMillis`             | | Sets the A register to the current runtime length in milliseconds
| `free`                      | [addr], [words] | Frees [words] at [addr]
