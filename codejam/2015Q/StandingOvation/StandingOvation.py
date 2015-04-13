import sys

T = int(sys.stdin.readline())
for t in range(T):
    _, s = sys.stdin.readline().split()
    ans = stands = 0
    gen = ((i, int(c)) for i, c in enumerate(s) if int(c) > 0)
    for i, c in gen:
        if stands < i:
            delta = i - stands
            ans += delta
            stands += delta
        stands += c
    print("Case #%d: %d" % (t+1, ans))
