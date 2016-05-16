class LRUCache(object):
    """
        @TODO
    """
    class Item(object):
        """
            @TODO
        """
        def __init__(self, key, value):
            assert isinstance(key, int)
            assert isinstance(value, int)
            assert value > 0
            self.__key = key
            self.__value = value

        def getValue(self):
            return self.__value

    def __init__(self, capacity):
        assert isinstance(capacity, int)
        assert capacity > 0
        print "Initialize for capacity: %d" % capacity
        self.__capacity = capacity
        self.__head = None
        self.__tail = None
        self.__map = {}

    def get(self, key):
        """
            @TODO
        """
        pass

    def set(self, key, value):
        """
            @TODO
        """
        pass
