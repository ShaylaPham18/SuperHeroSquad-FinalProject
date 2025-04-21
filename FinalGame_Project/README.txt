Infected-Hospital
SuperHeroSquad Final Project Spring 2025

This program will allow you to play a text-based adventure game that is based on an infected hospital.
It reads from a text files to get information: room.txt, puzzles.txt, items.txt, monsters.txt

For help to play the game, there is a help menu that will show when the command "help" is entered!
-----------------------------------------------------------------------------------------------------------
Used: Java and Java Swing
Created by Justin McCabe, Razan Abdalla, Shayla Pham, Jose Montejo
Created for Team Cobra's SRS
-----------------------------------------------------------------------------------------------------------
GAME FUNCTION
Player navigates throughout map picking up items,unlocking rooms,solving puzzles, and fighting monsters
Player can win game by getting to 1 of the exit rooms or by defeating all monsters and solving all puzzles
Player can lose game by being defeated in combat with a monster, by quitting the game without finishing it, or getting locked into a room
-----------------------------------------------------------------------------------------------------------
TEXT FILE FORMATS

room.txt
Format: roomID, roomName, roomDescription, exits, lockedRoom, unlockItem

roomID - String to represent id of the room
roomName - String to represent name of the room
roomDescription - String to describe the room
exits - A map of strings that represent the exits to the rooms
lockedRoom - Boolean to check if the room is locked and needs an item to enter
unlockItem - String to represent the item needed to unlock a locked room
-----------------------------------------------------------------------------------------------------------
puzzles.txt
Format: name|description|roomLocation|correctAnswer|resultWhenSolved|maxAttempts|hint|requiredItem

name - String to represent what the puzzle is called
description - String to explain/ describe the puzzle
roomLocation - String to show where the puzzle is located in what room
correctAnswer - String to show the correct answer of the puzzle
resultWhenSolved - String to show what happens when a puzzle is solved
maxAttempts - Int to represent how many attempts a puzzle gets
hint - String to give a hint to the player on how to solve the puzzle
requiredItem - String to tell what item would be needed to enter this room
-----------------------------------------------------------------------------------------------------------
items.txt
Format: itemID|itemName|itemType|itemStat|itemDescription|roomID|quantity

itemID - Int that represents the id of the item
itemName - String that represents the name of the item
itemType - String that represents what type of item the item is (ex: consumable)
itemStat - Int that represents a stat about the item (ex: healthPoint a consumable gives)
itemDescription - String that describes the item
roomID - String used to connect what items are located in what roomID
quantity - Int to show how many of the items are in the room
-----------------------------------------------------------------------------------------------------------
monsters.txt
Format: name|description|health|damageToPlayer|weaponWeaknesses|weaponDamages|spawnChance|spawnLocation|specialRule

name - String to represent the name of the monster
description - String to describe the monster
health - Int to represent the health of the monster
damageToPlayer - Int to represent how much damage the monster does to the player
weaponWeaknesses - String to represent what weapons affect the monster more
weaponDamages - Int to represent how much damage a weapon does
spawnChance - Int to show the chance of a monster spawning
spawnLocation - String to show where the monster spwans or is
specialRule - String to show the special things (if applicable) a monster does
-----------------------------------------------------------------------------------------------------------
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
-----------------------------------------------------------------------------------------------------------
MAP
Starting room-Emergency ward
Ending rooms-Exit tunnel,Roof
**The full map of the game can be seen in the HospitalMap.png.