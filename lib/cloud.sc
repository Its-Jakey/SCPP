#include <strings>

namespace cloud {
    var chars = "[[[[[[[[[[_ abcdefghijklmnopqrstuvwxyz1234567890)(*&^%$#@!<>?,.";

    public func setVar(varName, value) {
        _asm_("setCloudVar", varName, value);
    }
    public func getVar(varName) {
        return _asm_("getCloudVar", varName);
    }
    public func decompress(data, special_word) {
        var ret = "";

        for (i from 0 to strings::sizeOf(data) by 2) {
            var word = strings::join(strings::charAt(data, i), strings::charAt(data, i + 1));

            if (word == special_word) {
                i += 2;
                var wordToRepeat = strings::join(strings::charAt(data, i), strings::charAt(data, i + 1));

                if (wordToRepeat == special_word)
                    ret = strings::join(ret, special_word);
                if (wordToRepeat != special_word) {
                    i += 2;
                    var repeats = strings::join(strings::charAt(data, i), strings::charAt(data, i + 1));

                    for (repeat from 0 to repeats)
                        ret = strings::join(ret, wordToRepeat);
                }
            }
            if (word != special_word)
                ret = strings::join(ret, word);
        }

        return ret;
    }

    public func compress(input, special_word) {
        var len = strings::sizeOf(input);
        var ret = "";

        for (i from 0 to len by 2) {
            var word = strings::join(strings::charAt(input, i), strings::charAt(input, i + 1));
            var newWord = word;
            var count = 0;

            while (i < len && newWord == word) {
                count++;
                i += 2;
                newWord = strings::join(strings::charAt(input, i), strings::charAt(input, i + 1));
            }
            i -= 2;

            if (count > 2) {
                ret = strings::join(ret, special_word);
                ret = strings::join(ret, word);
                ret = strings::join(ret, count);
            }
            if (count < 3) {
                for (i2 from 0 to count) {
                    if (word == special_word) {
                        ret = strings::join(ret, special_word);
                        ret = strings::join(ret, special_word);
                    }
                    if (word != special_word)
                        ret = strings::join(ret, word);
                }
            }
        }
        return ret;
    }
    public func encode(value) {
        var ret = "";


        for (i from 0 to strings::sizeOf(value)) {

            var word = strings::indexOfChar(chars, strings::charAt(value, i));
            if (word == -1) {
                println(strings::join("Unsupported character ", strings::charAt(value, i)), strings::join("    at index ", i));
                exit();
            }
            ret = strings::join(ret, word);
        }
        return ret;
    }
    public func decode(value) {
        var ret = "";

        for (i from 0 to strings::sizeOf(value) by 2) {
            var idx = strings::join(strings::charAt(value, i), strings::charAt(value, i + 1));
            ret = strings::join(ret, strings::charAt(chars, idx));
        }
        return ret;
    }
}
