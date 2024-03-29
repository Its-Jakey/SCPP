
; Check that there is 2 arguments
LD @R0 @ARGS_LENGTH
LDI @R1 2
CMP @R0 @R1
JNZ @ARGS_ERROR

; Verify that the arguments are valid paths
LD @R15 @ARG_0
SYSCALL @GET_ABSOLUTE_PATH
MOV @R0 @R15
SYSCALL @FILE_EXISTS
JNE @PATH_ERROR

LD @R15 @ARG_1
SYSCALL @GET_ABSOLUTE_PATH
MOV @R1 @R15
SYSCALL @FILE_EXISTS
JNE @PATH_ERROR

; Remove the file
MOV @R15 @R0
SYSCALL @READ_FROM_FILE
MOV @R14 @R1
SYSCALL @WRITE_TO_FILE
MOV @R15 @R0
SYSCALL @REMOVE_FILE
HLT

:ARGS_ERROR
LDI @R15 'Invalid argument count'
SYSCALL @PRINT_STRING
HLT

:PATH_ERROR
LDI @R15 'Invalid file or directory: '
LD @R0 @ARGS_START
CON @R15 @R0
SYSCALL @PRINT_STRING
HLT