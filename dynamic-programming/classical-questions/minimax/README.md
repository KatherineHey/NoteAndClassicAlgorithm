---
description: '"Game Playing"  "Game Playing"'
---

# Minimax

After solving several "Game Playing" questions in leetcode, I find them to be pretty similar. Most of them can be solved using the **top-down DP** approach, which "brute-forcely" simulates every possible state of the game.

The key part for the top-down dp strategy is that we need to **avoid repeatedly solving sub-problems**. Instead, we should use some strategy to "remember" the outcome of sub-problems. Then when we see them again, we instantly know their result. By doing this, by applying the memo, we at most compute for every subproblem once, and there are `O(2^n)` subproblems, so the complexity is `O(2^n)` after memorization. \(Without memo, time complexity should be like `O(n!)`\)

