#!/usr/bin/python3

import sys


def main():
    chunk_size = 0
    mean = 0

    for line in sys.stdin:
        line = line.strip()
        line_split = line.split(',')
        if len(line_split) < 7:
            continue
        price = int(line_split[-7])
        chunk_size += 1
        mean += price

    if mean:
        mean /= chunk_size

    print(chunk_size, '\t', mean)


if __name__ == "__main__":
    main()
