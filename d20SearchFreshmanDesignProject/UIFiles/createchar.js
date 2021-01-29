// Name of File - createchar.js
// Author(s) - Cassidy Ashe, Chuck Kudzmas, Micheal Peterson
// Purpose - Javascript functions that populate create.html character creation table
// Dependencies - Firebase, JavaScript
// Date of last Edit - June 5, 2020

var firebaseConfig = {
    apiKey: "AIzaSyDwyMFRlotcN5DPsZXamZpnJa79DiPsREY",
    authDomain: "ciproject102team7.firebaseapp.com",
    databaseURL: "https://ciproject102team7.firebaseio.com",
    projectId: "ciproject102team7",
    storageBucket: "ciproject102team7.appspot.com",
    messagingSenderId: "30361040365",
    appId: "1:30361040365:web:30391fa131c36420c823e4",
    measurementId: "G-4TGP8PQNS4"
  };
  // Initialize Firebase
firebase.initializeApp(firebaseConfig);

//Alter Code for character creation
//Classes
function getClasses(){
    var classes = document.getElementById('classes').value;
    //var classes = 'Barbarian'
    classes = capitalize_Words(classes)
    firebase.database().ref('/Classes/' + classes).once('value').then(function(snapshot) {
    var classesinfo = snapshot.val();
    var keys = Object.keys(classesinfo)
    console.log(classesinfo);
    //var dl = document.getElementById('searchResults');
    //dl.innerHTML = '';
    //Allows for another search by clearing before printing
    
    //Code for specific order of Classes
    var className           = classesinfo.ClassName;
    var hitDice             = classesinfo.HitDie;
    var level1HP            = classesinfo.Level1HP;
    var level2HPDie         = classesinfo.Level2HPDie;
    var level2HPFlat        = classesinfo.Level2HPFlat;
    var focusAbility1       = classesinfo.FocusAbility1;
    var focusAbility2       = classesinfo.FocusAbility2;
    var armorProf1          = classesinfo.ArmorProf1;
    var armorProf2          = classesinfo.ArmorProf2;
    var armorProf3          = classesinfo.ArmorProf3;
    var armorProf4          = classesinfo.ArmorProf4;
    var weapProf1           = classesinfo.WeapProf1;
    var weapProf2           = classesinfo.WeapProf2;
    var tools               = classesinfo.Tools1;
    var savingThrow1        = classesinfo.SavingThrow1;
    var savingThrow2        = classesinfo.SavingThrow2;
    var skills1             = classesinfo.Skills1;
    var skills2             = classesinfo.Skills2;
    var skills3             = classesinfo.Skills3;
    var skills4             = classesinfo.Skills4;
    var skills5             = classesinfo.Skills5;
    var skills6             = classesinfo.Skills6;
    var startingEquips1a    = classesinfo.StartingEquips1a;
    var startingEquips1b    = classesinfo.StartingEquips1b;
    var startingEquips2a    = classesinfo.StartingEquips2a;
    var startingEquips2b    = classesinfo.StartingEquips2b;
    var startingEquips3a    = classesinfo.StartingEquips3a;
    var startingEquips3b    = classesinfo.StartingEquips3b;
    var startingEquips4     = classesinfo.StartingEquips4;
    var startingEquips5     = classesinfo.StartingEquips5;
    var feat1               = classesinfo.Feat1;
    var feat1Desc           = classesinfo.Feat1Desc;
    var feat2               = classesinfo.Feat2;
    var feat2Desc           = classesinfo.Feat2Desc;
    var startingGold        = classesinfo.StartingGold;
    var startingSpells      = classesinfo.StartingSpells;
    var cantrips            = classesinfo.Cantrips;
    var castingTrait        = classesinfo.CastingTrait;

    //Class Name
    document.getElementById('class').innerHTML = className;
    //Hit Dice
    document.getElementById('hitDie').innerHTML = hitDice;
    // //Level 1 HP
    document.getElementById('HP').innerHTML = level1HP;
    // //HP at Higher Levels Die
    document.getElementById('level2Die').innerHTML = level2HPDie;
    // //HP at Higher Levels Flat
    document.getElementById('level2Flat').innerHTML = level2HPFlat;
    // //Focus Ability 1
    //document.getElementById('class').innerHTML = className;
    // //Focus Ability 2
    //document.getElementById('class').innerHTML = className;
    // //Armor Prof 1
    document.getElementById('armorProf1').innerHTML = armorProf1;
    // //Armor Prof 2
    document.getElementById('armorProf2').innerHTML = armorProf2;
    // //Armor Prof 3
    document.getElementById('armorProf3').innerHTML = armorProf3;
    // //Armor Prof 4
    document.getElementById('armorProf4').innerHTML = armorProf4;
    // //Weap Prof 1
    document.getElementById('weaponProf1').innerHTML = weapProf1;
    // //Weap Prof 2
    document.getElementById('weaponProf2').innerHTML = weapProf2;
    // //Tools
    document.getElementById('tools').innerHTML = tools;
    // //Saving Throw 1
    document.getElementById('savingThrow1').innerHTML = savingThrow1;
    // //Saving Thorw 2
    document.getElementById('savingThrow2').innerHTML = savingThrow2;
    // //Skills 1
    document.getElementById('skill_1').innerHTML = skills1;
    // //Skills 2
    document.getElementById('skill_2').innerHTML = skills2;
    // //Skills 3
    document.getElementById('skill_3').innerHTML = skills3;
    // //Skills 4
    document.getElementById('skill_4').innerHTML = skills4;
    // //Skills 5
    document.getElementById('skill_5').innerHTML = skills5;
    // //Skills 6
    document.getElementById('skill_6').innerHTML = skills6;
    // //Starting Equips 1 A
    document.getElementById('startingEquips1a').innerHTML = startingEquips1a;
    // //Starting Equips 1 B
    document.getElementById('startingEquips1b').innerHTML = startingEquips1b;
    // //Starting Equips 2 A
    document.getElementById('startingEquips2a').innerHTML = startingEquips2a;
    // //Starting Equips 2 B
    document.getElementById('startingEquips2b').innerHTML = startingEquips2b;
    // //Starting Equips 3 A
    document.getElementById('startingEquips3a').innerHTML = startingEquips3a;
    // //Starting Equips 3 B
    document.getElementById('startingEquips3b').innerHTML = startingEquips3b;
    // //Starting Equips 4
    document.getElementById('startingEquips4').innerHTML = startingEquips4;
    // //Starting Equips 5
    document.getElementById('startingEquips5').innerHTML = startingEquips5;
    // //Feat 1
    document.getElementById('feat1').innerHTML = feat1;
    // //Feat 1 Description
    document.getElementById('feat1desc').innerHTML = feat1Desc;
    // //Feat 2
    document.getElementById('feat2').innerHTML = feat2;
    // //Feat 2 Description
    document.getElementById('feat2desc').innerHTML = feat2Desc;
    // //Starting Gold
    document.getElementById('gold').innerHTML = getStartingGold(startingGold);
    // //Starting Spells
    document.getElementById('startingSpells').innerHTML = startingSpells;
    // //Starting Cantrips
    document.getElementById('cantrips').innerHTML = cantrips;
    // //Casting Trait
    document.getElementById('castingtrait').innerHTML = castingTrait;

    });
}
//Alter Code for character creation.
//Race
function getRace(){
    var race = document.getElementById('races').value;
    //var race = 'Elf'
    race = capitalize_Words(race)
    firebase.database().ref('/Races/' + race).once('value').then(function(snapshot) {
    var racesinfo = snapshot.val();
    console.log(racesinfo);
    //var dl = document.getElementById('searchResults');
    //dl.innerHTML = '';
    //Allows for another search by clearing before printing
    
    //Code for specific order
    var racename            = racesinfo.Race;
    var abilityscore1       = racesinfo.Trait1;
    var abilityscore1bonus  = racesinfo.Trait1Inc;
    var abilityscore2       = racesinfo.Trait2;
    var abilityscore2bonus  = racesinfo.Trait2Inc;
    var age                 = racesinfo.Age;
    var size                = racesinfo.Size;
    var speed               = racesinfo.Speed;
    var darkVision          = racesinfo.DVision;
    var savingThrow         = racesinfo.SavingThrow;
    var resist              = racesinfo.Resist;
    var weapProf1           = racesinfo.WeapProf1;
    var weapProf2           = racesinfo.WeapProf2;
    var weapProf3           = racesinfo.WeapProf3;
    var weapProf4           = racesinfo.WeapProf4;
    var toolProf1           = racesinfo.ToolProf1;
    var toolProf2           = racesinfo.ToolProf2;
    var toolProf3           = racesinfo.ToolProf3;
    var profBonus           = racesinfo.ProfBonus;
    var languages           = racesinfo.Languages;
    var langTotal           = racesinfo.LanuagesTotal;
    var immune              = racesinfo.Immune;
    var raceSkill1          = racesinfo.RaceSkill1;
    var raceSkillDesc1      = racesinfo.RaceSkillDesc1;
    var raceSkill2          = racesinfo.RaceSkill2;
    var raceSkillDesc2      = racesinfo.RaceSkillDesc2;
    var special1            = racesinfo.Special1;
    var special2            = racesinfo.Special2;
    var special3            = racesinfo.Special3;
    var special4            = racesinfo.Special4;
    var special5            = racesinfo.Special5;
    
    //Race Name
    document.getElementById('race').innerHTML = racename;
    //Ability Score 1
    //document.getElementById('race').innerHTML = racename;
    // //Ability Score 1 Bonus
    //document.getElementById('race').innerHTML = racename;
    // //Ability Score 2
    //document.getElementById('race').innerHTML = racename;
    // //Ability Score 2 Bonus
    //document.getElementById('race').innerHTML = racename;
    // // Age
    document.getElementById('age').innerHTML = age;
    // // Size
    document.getElementById('size').innerHTML = size;
    // // Speed
    document.getElementById('speed').innerHTML = speed;
    // // Dark Vision
    document.getElementById('dvis').innerHTML = darkVision;
    // // Saving Throw
    document.getElementById('savingthrow').innerHTML = savingThrow;
    // // Resistances
    document.getElementById('resist').innerHTML = resist;
    // //Weapon Proficiency 1
    document.getElementById('weapProf1').innerHTML = weapProf1;
    // //Weapon Proficiency 2
    document.getElementById('weapProf2').innerHTML = weapProf2;
    // //Weapon Proficiency 3
    document.getElementById('weapProf3').innerHTML = weapProf3;
    // //Weapon Proficiency 4
    document.getElementById('weapProf4').innerHTML = weapProf4;
    // //Tool Proficiency 1
    document.getElementById('tool1').innerHTML = toolProf1;
    // //Tool Proficiency 2
    document.getElementById('tool2').innerHTML = toolProf2;
    // //Tool Proficiency 3
    document.getElementById('tool3').innerHTML = toolProf3;
    // //Proficiency Bonus
    document.getElementById('probonus').innerHTML = profBonus;
    // //Languages
    document.getElementById('langs').innerHTML = langTotal;
    // //Total Languages
    //document.getElementById('race').innerHTML = racename;
    // //Immunities
    document.getElementById('immune').innerHTML = immune;
    // //Race Skill 1
    document.getElementById('raceFeat1').innerHTML = raceSkill1;
    // //Race Skill 1 Description
    document.getElementById('raceFeat1desc').innerHTML = raceSkillDesc1;
    // //Race Skill 2
    document.getElementById('raceFeat2').innerHTML = raceSkill2;
    // //Race Skill 2 Description
    document.getElementById('raceFeat2desc').innerHTML = raceSkillDesc2;
    // //Special 1
    document.getElementById('special1').innerHTML = special1;
    // //Special 2
    document.getElementById('special2').innerHTML = special2;
    // // Special 3
    document.getElementById('special3').innerHTML = special3;
    // // Special 4
    document.getElementById('special4').innerHTML = special4;
    // // Special 5
    document.getElementById('special5').innerHTML = special5;
    });
}
function getCharacterStats() {
    var rollone = 0;
    var rolltwo = 0;
    var rollthree = 0;
    var rollfour = 0;
    var rollfive = 0;
    var rollsix = 0;
    // Number of dice
    var rolls = 6;
    var math = [];
    for (var step = 0; step < rolls; step++) {
        // one full stat roll
            var a = Math.floor(Math.random() * (6)) + 1;
            var b = Math.floor(Math.random() * (6)) + 1;
            var c = Math.floor(Math.random() * (6)) + 1;
            var d = Math.floor(Math.random() * (6)) + 1;
            console.log(a,b,c,d)
            if (a<=b && a<=c && a<=d){
                var value = (b+c+d)
                if (rollone == 0){
                    rollone = value;
                }
                else if (rolltwo == 0){
                    rolltwo = value;
                }
                else if (rollthree == 0){
                    rollthree = value;
                }
                else if (rollfour == 0){
                    rollfour = value;
                }
                else if (rollfive == 0){
                    rollfive = value;
                }
                else if (rollsix == 0){
                    rollsix = value;
                }
            }
            else if (b<=a && b<=c && b<=d){
                var value = (a+c+d)
                if (rollone == 0){
                    rollone = value;
                }
                else if (rolltwo == 0){
                    rolltwo = value;
                }
                else if (rollthree == 0){
                    rollthree = value;
                }
                else if (rollfour == 0){
                    rollfour = value;
                }
                else if (rollfive == 0){
                    rollfive = value;
                }
                else if (rollsix == 0){
                    rollsix = value;
                }
            }
            else if (c<=a && c<=b && c<=d){
                var value = (a+b+d)
                if (rollone == 0){
                    rollone = value;
                }
                else if (rolltwo == 0){
                    rolltwo = value;
                }
                else if (rollthree == 0){
                    rollthree = value;
                }
                else if (rollfour == 0){
                    rollfour = value;
                }
                else if (rollfive == 0){
                    rollfive = value;
                }
                else if (rollsix == 0){
                    rollsix = value;
                }
            }
            else if (d<=a && d<=b && d<=c){
                var value = (a+b+c)
                if (rollone == 0){
                    rollone = value;
                }
                else if (rolltwo == 0){
                    rolltwo = value;
                }
                else if (rollthree == 0){
                    rollthree = value;
                }
                else if (rollfour == 0){
                    rollfour = value;
                }
                else if (rollfive == 0){
                    rollfive = value;
                }
                else if (rollsix == 0){
                    rollsix = value;
                }
            }
        }
        console.log(rollone, rolltwo, rollthree, rollfour, rollfive, rollsix)
        document.getElementById('strength').innerHTML   = rollone;
        document.getElementById('dexterity').innerHTML   = rolltwo;
        document.getElementById('constitution').innerHTML   = rollthree;
        document.getElementById('intelligence').innerHTML   = rollfour;
        document.getElementById('wisdom').innerHTML   = rollfive;
        document.getElementById('charisma').innerHTML   = rollsix;
    }

function getStartingGold(dbgold){
    var splitdb = dbgold.split(" ", 4);
    var d4rolls = splitdb[0];
    var gold = 0
    for (var step = 0; step < d4rolls; step++ ){
        gold = Math.floor(Math.random() * 4) + 1 + gold;
    }
    if (splitdb[3] == 10 ){;
        var times = splitdb[3];
        gold = times * gold
    }
    return gold
}
function statBonus(roll){
    if (roll == 1){
        abilitymod = -5;
        return abilitymod;
    }
    else if (roll == 2 || roll == 3){
        abilitymod = -4;
        return abilitymod;
    }
    else if (roll == 4 || roll == 5){
        abilitymod = -3;
        return abilitymod;
    } 
    else if (roll == 6 || roll == 7){
        abilitymod = -2;
        return abilitymod;
    }
    else if (roll == 8 || roll == 9){
        abilitymod = -1;
        return abilitymod;
    }
    else if (roll ==10 || roll == 11){
        abilitymod = 0;
        return abilitymod;
    }
    else if (roll ==12 || roll == 13){
        abilitymod = 1;
        return abilitymod;
    }
    else if (roll ==14 || roll == 15){
        abilitymod = 2;
        return abilitymod;
    }
    else if (roll ==16 || roll == 17){
        abilitymod = 3;
        return abilitymod;
    }
    else if (roll ==18 || roll == 19){
        abilitymod = 4;
        return abilitymod;
    }
    else if (roll ==20 || roll == 21){
        abilitymod = 5;
        return abilitymod;
    }
    else if (roll ==22 || roll == 23){
        abilitymod = 6;
        return abilitymod;
    }
    else if (roll ==24 || roll == 25){
        abilitymod = 7;
        return abilitymod;
    }
    else if (roll ==26 || roll == 27){
        abilitymod = 8;
        return abilitymod;
    }
    else if (roll ==28 || roll == 29){
        abilitymod = 9;
        return abilitymod;
    }
    else if (roll ==30){
        abilitymod = 10;
        return abilitymod;
    }
} 

function runStatBonus(){
    var str = document.getElementById('strength').innerHTML;
    var dex = document.getElementById('dexterity').innerHTML;
    var con = document.getElementById('constitution').innerHTML;
    var int = document.getElementById('intelligence').innerHTML;
    var wis = document.getElementById('wisdom').innerHTML;
    var chr = document.getElementById('charisma').innerHTML;
    
    document.getElementById('strbonus').innerHTML = statBonus(str);
    document.getElementById('dexbonus').innerHTML = statBonus(dex);
    document.getElementById('conbonus').innerHTML = statBonus(con);
    document.getElementById('intbonus').innerHTML = statBonus(int);
    document.getElementById('wisbonus').innerHTML = statBonus(wis);
    document.getElementById('chrbonus').innerHTML = statBonus(chr);
}
//From https://www.w3resource.com/javascript-exercises/javascript-string-exercise-9.php
function capitalize_Words(str) {
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}
