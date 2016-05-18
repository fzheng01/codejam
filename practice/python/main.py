from cache.LRUCache import LRUCache

if __name__ == '__main__':
    cache = LRUCache(2)
    cache.set(2, 1)
    print cache.peekAll()
    cache.set(1, 1)
    print cache.peekAll()
    cache.get(2)
    print cache.peekAll()
    cache.set(4, 1)
    print cache.peekAll()
    cache.get(1)
    print cache.peekAll()
    node = cache.get(2)
    print cache.peekAll()