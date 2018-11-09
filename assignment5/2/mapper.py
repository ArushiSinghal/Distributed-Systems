#!/usr/bin/env python
import sys

#num = int(sys.argv[1])
for line in sys.stdin:
    line = line.strip()
    words = line.split()
    for word in words:
        p = len(word)
        #if (p == num):
        print '%s\t%s' % (p, 1)
