#!/usr/bin/python3

import sys


def main():
    chunk_size = 0
    mean = 0
    var = 0
    prices = []

    for line in sys.stdin:
        line = line.strip()
        line_split = line.split(',')
        if len(line_split) < 7:
            continue
        price = int(line_split[-7])
        prices.append(price)
        chunk_size += 1
        mean += price

    if chunk_size:
        mean /= chunk_size

        for price in prices:
            var += (price - mean) ** 2

        var /= chunk_size
    print(chunk_size, '\t', mean, '\t', var)


if __name__ == "__main__":
    main()
