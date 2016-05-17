class LRUCache(object):
    """
        @todo: abc
    """
    class Item(object):
        """
            @todo: abc
        """
        def __init__(self, key, value):
            assert isinstance(key, int)
            assert isinstance(value, int)
            assert value > 0
            self.__key = key
            self.__value = value
            self.__pre = None
            self.__next = None

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
            @todo: abc
        """
        pass

    def set(self, key, value):
        """
            @todo: abc
        """
        pass

    def __remove(self, item):
        """
            @todo: abc
        """
        pass

    def __add_header(self, item):
        """
            @todo: abc
        """
        pass

