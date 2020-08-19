# Current endpoints


## /heroes
### get
returns a Json of all of the heroes
[
  {
    "img": "http://cdn.dota2.com/apps/dota2/images/heroes/antimage_full.png?",
    "attacktype": "Melee",
    "hero_id": "1",
    "hero_name": "Anti-Mage",
    "primary_atri": {
      "primary_atri": "agi"
    }
  },
]

### put  (dotaheroController)
Requires that you are logged in
Updates a hero based off of their id allowing for edits
{
    "img": "http://cdn.dota2.com/apps/dota2/images/heroes/antimage_full.png?",
    "attacktype": "Melee",
    "hero_id": "1",
    "hero_name": "Anti-Mage",
    "primary_atri": 
    {
      "primary_atri": "agi"
    }
}

### get /heroes/item/{hero id}
Returns all items for a given hero as well as there stages
[
    {
        stage:
        Item:{
            img:
            notes:
            price:
            item_id:
            item_name:
        }
    },
]
### get /heroes/abilities/{hero id}
takes in a heroes id and returns their abilities
[
  {
    "ability_name": "Mana Break",
    "img": "http://cdn.dota2.com/apps/dota2/images/abilities/antimage_mana_break_md.png"
  },
]

### get /heroes/counter/{hero_id}
Returns the heroes best 3 and worst 3 heroes it counters 
{
  "best1": {
    "img": "http://cdn.dota2.com/apps/dota2/images/heroes/visage_full.png?",
    "attacktype": "Ranged",
    "hero_id": "92",
    "primary_atri": {
      "primary_atri": "int"
    },
    "hero_name": "Visage"
  },
}

### put /heroes/counter 
MUST BE LOGGED IN TO HAVE ACCESS
Lets you assign new heroes who they are best and worst against
{
    "refHero": "1",
    "best1": "2",
    "best2": "3",
    "best3": "4",
    "worst1": "5",
    "worst2": "6",
    "worst3": "7"
}

## /attributes
### get
returns all attributes 
[
  {
    "primary_atri": "str"
  },
  {
    "primary_atri": "agi"
  },
  {
    "primary_atri": "int"
  }
]

## /stage
### get 
Returns a list of all stages
[
  {
    "stage": "Early"
  },
  {
    "stage": "Mid"
  },
  {
    "stage": "Late"
  },
  {
    "stage": "Start"
  }
]

## /user
### /post
Returns a jsonwebtoken 
{
    "username":"User",
    "password":"test"
}
## /user/role
### /post
Create a new user
MUST BE LOGGED IN AS AN ADMIN TO POST TO CREATE NEW USERS
{
    "username":,
    "password":
}


