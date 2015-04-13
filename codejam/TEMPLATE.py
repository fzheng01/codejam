import sys

T = int(sys.stdin.readline())
for t in range(T):
    """
        command:
            python TEMPLATE.py < test.in > test.out
        splitted integers:
            X,R,C = map(int, sys.stdin.readline().split())
        list:
            L = list(map(int, sys.stdin.readline().split()))
        string:
            S = sys.stdin.readline().strip()
        partial string:
            _, S = sys.stdin.readline().split()
    """
    ans = None
    print("Case #%d: %s" % (t+1, ans))
    # print("Case #%d: %d" % (t+1, ans))
