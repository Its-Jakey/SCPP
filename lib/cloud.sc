#include <strings>
#include <math>

public namespace cloud {
    var chars = "[[[[[[[[[[_ abcdefghijklmnopqrstuvwxyz1234567890)(*&^%$#@!<>?,.";

    public func setVar(varName, value) {
        _asm_("setCloudVar", varName, value);
    }
    public func getVar(varName) {
        return _asm_("getCloudVar", varName);
    }
    public func decompress(data, special_word, wordLen, repeatLen) {
        var ret = "";
        var len = strings::sizeOf(data);
        var i = 0;

        while (i < len) {
            var word = strings::substring(data, i, i + wordLen);

            if (word == special_word) {
                i += wordLen;

                var wordToRepeat = strings::substring(data, i, i + wordLen);
                i += wordLen;

                var repeats = strings::substring(data, i, i + repeatLen);
                i += repeatLen;

                for (repeat from 0 to repeats)
                    ret ..= wordToRepeat;
            } else {
                ret ..= word;
                i += wordLen;
            }
        }

        return ret;
    }

    public func compress(input, special_word, wordLen, repeatLen) {
        var maxRepeat = "1"..strings::repeat("0", repeatLen);
        var minRepeats = math::ceil((wordLen + repeatLen) / wordLen);
        
        var len = strings::sizeOf(input);
        var ret = "";
        var i = 0;

        while (i < len) {
            var word = strings::substring(input, i, i + wordLen);
            var newWord = word;
            var count = 0;

            while ((i < len) && (newWord == word) && (count < maxRepeat)) {
                count++;
                i += wordLen;
                newWord = strings::substring(input, i, i + wordLen);
            }

            if (count > minRepeats)
                ret ..= special_word..word..strings::repeat("0", repeatLen - strings::sizeOf(count))..count;
            else 
                ret ..= strings::repeat(word, count);
        }
        return ret;
    }
    public func encode(value) {
        var ret = "";


        for (i from 0 to strings::sizeOf(value)) {

            var word = strings::indexOfChar(chars, strings::charAt(value, i));
            if (word == -1) {
                println("Unsupported character ", strings::charAt(value, i), " at index ", i);
                exit();
            }
            ret = ret..word;
        }
        return ret;
    }
    public func decode(value) {
        var ret = "";

        for (i from 0 to strings::sizeOf(value) by 2) {
            var idx = strings::charAt(value, i)..strings::charAt(value, i + 1);

            if (idx != 0)
                ret = ret..strings::charAt(chars, idx);
        }
        return ret;
    }
}
