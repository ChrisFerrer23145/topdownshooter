# topdownshooter
# Controls: 
	Movement - WASD
	
	Switch Weapons - F
# Current Weapons:
	Bullet - does 10 damage

	Lure - all enemies within range of this will be lured to it instead of to the character. 10 second cooldown, expires after 15 seconds

	Grenade - after 3 seconds, all enemies within 300 pixels of this explosive will take 75 damage. 5 second cooldown
# Special Mechanics:
	After every 10 kills, player gains +15 health. If the player is above 85 health, this simply raises their health to 100

	After every 15 kills, player gains 1 armor. Armor decreases how often an enemy will do damage to a player. Player will take damage every tick without armor, but once every other tick with one level of armor, once every 3 ticks with 2 levels of armor, etc.
