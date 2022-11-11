 

public namespace sound {
    public func playSound(sound) {
        _asm_("playSound", sound);
    }
    public func startSound(sound) {
        _asm_("startSound", sound);
    }
    public func stopSounds() {
        _asm_("stopSounds");
    }
    public func setPitch(pitch) {
        _asm_("setPitch", pitch);
    }
    public func setVolume(volume) {
        _asm_("setVolume", volume);
    }
}
