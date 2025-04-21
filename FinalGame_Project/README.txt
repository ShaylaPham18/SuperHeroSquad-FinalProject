Infected-Hospital
SuperHeroSquad final project spring 2025
Text based Interactive game that functions on user input
---------------------------------------
Used: Java and Java Swing Why: Because we had to
Created by Justin McCabe, Razan Abdalla, Shayla Pham, Jose Montejo
Created for Team Cobra (idk their names)
---------------------------------------
GAME FUNCTION
Player navigates throughout map picking up items,unlocking rooms,solving puzzles, and fighting monsters
Player can win game by getting to 1 of the exit rooms or by defeating all monsters and solving all puzzles
Player can lose game by being defeated in combat with a monster, by quitting the game without finishing it, or getting locked into a room
---------------------------------------
TEXT FILE FORMATS
Room->RoomID,roomName,roomDescription,Exits2nextRoomId,lockStatus(if applicable),itemToUnlockRoom(if applicable)
Item->ItemID,ItemName,itemType,itemStat,itemDescription,roomItemIsIn,numberOfItems
puzzle->puzzleName,puzzleDescription,roomPuzzleIsIn,AnswerToPuzzle,correctPuzzleResult,puzzleAttemptsAllowedBeforeHint,puzzleHint,itemNeededToSolvePuzzle
Monsters->monsterName,monsterDescription,monsterHealth,monsterDamage,monsterWeaponWeakness,weaponWeaknessDamage
---------------------------------------
WHAT WENT WELL
---------------------------------------
WHAT WENT WRONG
---------------------------------------
COMMANDS
type help to view this help menu
	type map to view the map
	type quit to end the game
	type stat stats or st to view player stats
	type inventory or inv to view player inventory (MAX 15 items)

	NAVIGATION-type go + direction you want to go (north south east west n s e w) not case sensitive
	IN ROOM-type explore or ex to gather information about the room (tells you if there's items or puzzles in room)
	IN ROOM-type inspect or ins to view items and puzzles in the room(shows you the items and puzzles in room)

	ITEMS-type take + itemName or itemNameShortcut to pick up an item
	To take multiple ITEMS type take + itemName or itemNameShortcut + number you want to pick up
	ITEMS-type drop + itemName or itemNameShortcut to drop a item
	To drop multiple ITEMS type take + itemName or itemNameShortcut + number you want to drop
	To use an ITEM type use + itemName or itemNameShortCut to use a CONSUMABLE ITEM
	Some rooms are locked and require an item to be in your inventory for room to be unlocked

	COMBAT-When entering room with monster you will have 2 options
	type yes to enter combat with the monster	typing anything else will avoid monster
	If yes will show you 4 options (attack with weapon,use item,flee,equip weapon)
	attack with weapon causes fight cycle causing you and monster damage
	use item lets you use a non weapon item during combat (mostly for healing)
	flee lets you escape the combat
	equip weapon lets you equip weapon for more damage (attack with weapon with no equipped is fists)
	You can type help during combat for info in console(Fleeing and ignoring keep monster in room)

	PUZZLE-When in room with puzzle after inspect it will say 'You uncover a hidden mechanism... It's a puzzle!'
	You can type try to attempt the puzzle which will display the puzzle and how to complete it
	You will be then giving multiple options solve hint leave use item inventory
	SOLVE-lets you type in answer (or item) to the puzzle
	HINT-After 3 failed attempts you are allowed to use hint which gives you a hint
	LEAVE-Leaves the puzzle and goes back to navigation
	USE ITEM-Some puzzles require you to use an item to solve it so you type in full itemName-> itemName (this part to)
	INVENTORY-Checks player's inventory
---------------------------------------
MAP
Starting room-Emergency ward
Ending rooms-Exit tunnel,Roof