 
namespace time {
    public func sleep(millis) {
        _asm_("sleep", millis);
    }
    public func getRuntimeMillis() {
        return _asm_("runtimeMillis");
    }
}
