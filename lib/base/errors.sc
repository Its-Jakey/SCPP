

public namespace errors {
    public func throw(message) {
        println(concat("Error: ", message));
        exit();
    }
    public func throwTypeError(expected, got) {
        println(concat("TypeError: ", expected, ""));
    }
}
