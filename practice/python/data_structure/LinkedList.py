class LinkedList(object):
    def __init__(self, key, value):
        self.__key = key
        self.__value = value
        self.__next = None

    def get_value(self):
        return self.__value

    def get_key(self):
        return self.__key

    def __repr__(self):
        return '{%d: %d}' % (self.__key, self.__value)

    __slots__ = (
                 "__key",
                 "__value",
                 "__next",
                 )