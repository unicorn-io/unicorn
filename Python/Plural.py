import re


def build_match_apply_rule(pattern, search, replace):
    def match_found(word):
        return re.search(pattern, word)

    def apply_match(word):
        return re.sub(search, replace, word)
    return match_found, apply_match


class Plural:
    
    '''
    This class provides an Object that converts a noun to its plural!
    This class also returns an iterator of rules over which the plural function work.
    '''


    filename = "patterns.txt"

    def __init__(self):
        self.cache = []
        self.file = open(self.filename)

    def __iter__(self):
        self.cache_index = 0;
        return self

    def __next__(self):
        self.cache_index += 1
        if len(self.cache) >= self.cache_index:
            return self.cache[self.cache_index - 1]

        if self.file.closed:
            raise StopIteration

        line = self.file.readline()
        if not line:
            self.file.close()
            raise StopIteration

        pattern, search, replace = line.split(None, 2)
        func = build_match_apply_rule(pattern, search, replace)
        self.cache.append(func)
        return func


rules = Plural()
def plural(noun):
    for rule in rules:
        match_found, apply_match = rule
        print(rule)
        if match_found(noun):
            return apply_match(noun)


