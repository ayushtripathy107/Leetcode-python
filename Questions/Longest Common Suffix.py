class TrieNode:
    def __init__(self):
        self.children = {}
        self.best_index = -1

class Solution(object):
    def stringIndices(self, wordsContainer, wordsQuery):
        root = TrieNode()
        
        # Initialize root with the absolute best default index (for empty suffix match)
        best_root_idx = 0
        for i in range(1, len(wordsContainer)):
            if len(wordsContainer[i]) < len(wordsContainer[best_root_idx]):
                best_root_idx = i
        root.best_index = best_root_idx
        
        # Insert words in reverse order into the Trie
        for idx, word in enumerate(wordsContainer):
            curr = root
            # Traverse from back to front
            for char in reversed(word):
                if char not in curr.children:
                    curr.children[char] = TrieNode()
                curr = curr.children[char]
                
                # Update the best index for this suffix prefix
                if curr.best_index == -1:
                    curr.best_index = idx
                elif len(word) < len(wordsContainer[curr.best_index]):
                    curr.best_index = idx
                    
        # Process each query
        ans = []
        for query in wordsQuery:
            curr = root
            for char in reversed(query):
                if char in curr.children:
                    curr = curr.children[char]
                else:
                    break
            ans.append(curr.best_index)
            
        return ans
