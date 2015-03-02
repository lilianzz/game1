# game1
# game1 simple shades

A field of play where the blocks move.
The grid where the blocks lay. There are four columns.

"live" blocks that are player controlled: 
Falling blocks first appear at the top of the grid and then fall due to gravity. 
The blocks are marked with different number from 1 to 5. 

"dead" blocks that are no longer player controlled: 
This is the set of blocks that are resting on the ground due to gravity. 
2 blocks of the same number on top of each other will combine into a single block of a larger number.

A scoring system.
You get points for filling rows with blocks of same number.

A win or fail state.
You fail when a block would spawn in a resting state.

A control mechanism.
The player can move the live block left and right.