//Created by @MinekPo1 https://github.com/MinekPo1

#include <time>

namespace benchmark {
    func tpoly(n) {
        var mu = 10;
        var x = 0.2;
        var s;
        var pu = 0;
        var su;
        var pol = malloc(100);

        for (i from 0 to n) {
            s = 0;
            for (j from 0 to 100) {
                mu = (mu + 2) / 2;
                pol[j] = mu;
            }
            for (j from 0 to 100)
                s = (x * s) + pol[j];
            pu += s;
        }
        return pu;
    }

    public func main() {
        println("tpoly benchmark");

        var start = time::getRuntimeMillis();
        println(tpoly(5000));
        println("done in "..((time::getRuntimeMillis() - start) / 1000).."s");
    }
}