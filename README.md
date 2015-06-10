# EvernoteInterview

This was a program I wrote for an evernote interview. The problem was as follows:

Write a function that takes two parameters:
(1) a String representing the contents of a text document
(2) an integer providing the number of items to return

- Implement the function such that it returns a list of Strings ordered by word frequency, the most frequently occurring word first.
- Use your best judgement to decide how words are separated.
- Implement this function as you would for a production / commercial system
- You may use any standard data structures.
- A solution in Java is preferred.

Performance Constraints:
- Your solution should run in O(n) time where n is the number of characters in the document.
- Please provide reasoning on how the solution obeys the O(n) constraint.


MY SOLUTION:

1)Take the characters one at a time and putting them into a trie (http://en.wikipedia.org/wiki/Trie)
      This gives O(n) where number of chars = n

2)Get the words(along with their frequency) out of the trie by iterating over the nodes
      This gives O(n) because each node represents 1 character

3)Add the words into my partial sorting heap (http://en.wikipedia.org/wiki/Partial_sorting)
      This gives O(m) where number of words = m. Since m <= n (you can't have more words than characters) this bounded by O(n)
      The heap itself is on the order O(m*logk) where k is the number of output and m is the number of words we feed in.
      This is unacceptable according to the problem, so I'm revisiting it now :) Looking into implementing radix sort.
