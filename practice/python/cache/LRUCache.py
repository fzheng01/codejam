class LRUCache(object):
    """
        LRU Cache
    """
    class Item(object):
        """
            Cache item
        """
        def __init__(self, key, value):
            """
                Initializes the L{Item} for the cache
                @param key: the key to retrieve the item
                @type key: int
                @param value: the item value
                @type value: int
                @precondition: value > 0
            """
            assert isinstance(key, int)
            assert isinstance(value, int)
            assert value > 0
            self.__key = key
            self.__value = value
            self.pre = None
            self.next = None

        def getKey(self):
            """
                Gets the key of the L{Item}
                @rtype: int
            """
            return self.__key

        def getValue(self):
            """
                Gets the value of the L{Item}
                @rtype: int
                @postcondition: return > 0
            """
            return self.__value
        
        def __repr__(self):
            return '{%d: %d}' % (self.__key, self.__value)

    def __init__(self, capacity):
        """
            Initializes the L{LRUCache}
            @param capacity: the cache capacity
            @type capacity: int
            @precondition: capacity > 0
        """
        assert isinstance(capacity, int)
        assert capacity > 0
        print "Initialize for capacity: %d" % capacity
        self.__capacity = capacity
        self.__head = None
        self.__tail = None
        self.__map = {}

    def get(self, key):
        """
            Gets the item value by L{key},
            if L{key} is not found, returns -1
            @param key: the key to retrieve item value
            @type key: int
            @rtype: int
        """
        assert isinstance(key, int)
        if key in self.__map:
            item = self.__map[key]
            self.__remove(item)
            self.__prepend(item)
            return item.getValue()
        return -1

    def set(self, key, value):
        """
            Sets the item into L{LRUCache}
            if the capacity has been reached, removes the least recently used item
            @param key: the item key
            @type key: int
            @param value: the item value
            @type value: int
            @precondition: value > 0
        """
        assert isinstance(key, int)
        assert isinstance(value, int)
        assert value > 0
        new_item = self.Item(key, value)
        if key in self.__map:
            old_item = self.__map[key]
            self.__remove(old_item)
        else:
            if self.__capacity == 0:
                last_item = self.__tail
                self.__map.pop(last_item.getKey(), None)
                self.__remove(last_item)
        self.__prepend(new_item)
        self.__map[key] = new_item

    def peekAll(self):
        """
            Peeks the value of the most recently used item
            @rtype: int
            @postcondition: return > 0
        """
        res = []
        node = self.__head
        while node:
            res.append(node)
            node = node.next
        return res

    def __remove(self, item):
        """
            Removes the L{item} from the L{LRUCache}
            @type item: Item
        """
        assert item is not None
        if item.pre is not None:
            item.pre.next = item.next
        else:
            self.__head = item.next
        if item.next is not None:
            item.next.pre = item.pre
        else:
            self.__tail = item.pre
        self.__capacity += 1

    def __prepend(self, item):
        """
            Prepends L{item} to the L{LRUCache}
            @type item: Item
        """
        assert item is not None
        item.pre = None
        item.next = self.__head
        if self.__head is not None:
            self.__head.pre = item
        self.__head = item
        if self.__tail is None:
            self.__tail = item
        self.__capacity -= 1

    __slots__ = (
                 "__capacity",
                 "__map",
                 "__head",
                 "__tail",
                 )