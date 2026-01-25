# Builder's Utils: Transform (BTW:CE Addon)
## Features
Every command save the blocks, tile entities and entities data.

### Commands
- /copy [x1/y1/z1] [x2/y2/z2]: copy a selection*

- /paste [x/y/z]: paste a copied selection (to a specified coord or else to the player's coord)
  
- /cut [x1/y1/z1] [x2/y2/z2]: cut a selection*
  
- /move [to|add] [x/y/z]: move a selection§ 'to a specified coords' or 'shifted by specified amount' or else to the player's coord

- /remove [x1/y1/z1] [x2/y2/z2]: remove a selection*

- /rotate [x1/y1/z1] [x2/y2/z2] [true|false]: rotate a selection* clockwise unless specified with a 'false'

- /stack [+x|-x|+y|-y|+z|-z] [number]: duplicate [amount] of time a selection§ in either the player's camera direction or the specified direction

- /editSpeed <speed>: set the editing speed, by default at 100 blocks per tick (undo and redo are using x10 this speed), only singleplayer

- /undo: undo the previous action

- /redo: redo the previously reverted action

- /pos1 [x/y/z]: set the first position to the specified coord

- /pos2 [x/y/z]: set the second position to the specified coord

- /posAll [x1/y1/z1] [x2/y2/z2]: set all positions to the specified coords

[] = optional

<> = needed

| = OR

\* = either current selection or specified coords

§ = only current selection
  
### Wand
Use the wooden axe as a selection tool.

### Selection display
The selection will be highlighed with a framed box.

### Screenshots
Selection:
![Selection display](https://cdn.modrinth.com/data/cached_images/ad13e9325dd238b9d82dd8f0ba94517a88c61b33.jpeg)
Rotated:
![Rotated build](https://cdn.modrinth.com/data/cached_images/06dafbe20a6182b81343382e7f65aa782430cbfa.jpeg)
Moved:
![Moved build](https://cdn.modrinth.com/data/cached_images/d77d18d182b44e7e11226864966dd1664feb110f.jpeg)
Stacked:
![Stacked build](https://cdn.modrinth.com/data/cached_images/76344238de653ca310646a56f46e49eab3261a87.jpeg)
Selected jungle:
![Selected jungle](https://cdn.modrinth.com/data/cached_images/da6d644340c2b7df7b382fa6191630090e9348e5.jpeg)
Removed jungle:
![Removed jungle](https://cdn.modrinth.com/data/cached_images/c83cc03737673ba57955cc0053f4a892bc39ca66.jpeg)

### Works in multiplayer

## Found a bug or have suggestions ?

https://github.com/BTW-Community/Builders-Utils-Transform

(Known issue: Windmill and waterwheel doesn't spawn properly when pasted)


## Download: 
https://modrinth.com/mod/builders-utils-transform/versions
