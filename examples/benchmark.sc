//Created by @MinekPo1 https://github.com/MinekPo1

#include <math>
#include <time>

namespace benchmark {
    func is_prime(num){
        for(i from 2 to math::floor(math::sqrt(num),0) + 1){
            if((num % i) == 0) {
                return 0;
            }
        }
        println(num);
        return 1;
    }

    public func main() {
        var i = 3;
        var c = 1;

        while (c < 1000){
            if (is_prime(i))
                c = c + 1;
            i = i + 1;
        }

        var t = time::getRuntimeMillis();

        print("done in ");
        print(t / 1000);
        print("s");
    }
}