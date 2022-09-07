
; Check that there is at least 1 argument
LD @R0 @ARGS_LENGTH
LDI @R1 1
CMP @R0 @R1
JNZ @ARGS_ERROR

; Verify that the argument is a valid path
LD @R15 @ARGS_START
SYSCALL @GET_ABSOLUTE_PATH
MOV @R0 @R15
SYSCALL @FILE_EXISTS
JNE @PATH_ERROR

; Make sure the path is not a directory
MOV @R15 @R0
SYSCALL @FILE_IS_DIRECTORY
JE @PATH_ERROR

; Print the contents of the file
MOV @R15 @R0
SYSCALL @READ_FROM_FILE
SYSCALL @PRINT_STRING
HLT

:ARGS_ERROR
LDI @R15 'Invalid argument count'
SYSCALL @PRINT_STRING
HLT

:PATH_ERROR
LDI @R15 'Invalid file path: '
LD @R0 @ARGS_START
CON @R15 @R0
SYSCALL @PRINT_STRING
HLT