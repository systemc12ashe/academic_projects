// Name of File - fire_search.js
// Author(s) - Cassidy AudioScheduledSourceNode, Chuck Kudzmas, Michael Peterson
// Purpose - JavaScipt file, contains functions and variables for search.html outputs and create.html table
// Dependencies - Javascript, Firebase
// Date of last Edit - April 23, 2020

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


function getData(){
    var spell = document.getElementById('searchValue').value;
    spell = capitalize_Words(spell);
    firebase.database().ref('/Spells/' + spell).once('value').then(function(snapshot) {
    var spellinfo = snapshot.val();
    var keys = Object.keys(spellinfo);
    console.log(spellinfo);
    var dl = document.getElementById('searchResults');
    dl.innerHTML = '';
    //Allows for another search by clearing before printing
    
    //Cassidy's Code before setting in up for specific order
    //for (const property in spellinfo) {
    //    var dt = document.createElement('dt');
    //    var ttext = document.createTextNode(property);
    //    dt.appendChild(ttext);
    //    var dd = document.createElement('dd');
    //    var dtext = document.createTextNode(spellinfo[property]);
    //    dd.appendChild(dtext);
    //    dl.appendChild(dt);
    //    dl.appendChild(dd);
    //    console.log(`${property}: ${spellinfo[property]}`);
    //}

    //Chuck's Code to have it all in a specific order
        var name            = spellinfo.SpellName;
        var spellLevel      = spellinfo.SpellLevel;
        var spellSchool     = spellinfo.SpellSchool;
        var castingTime     = spellinfo.CastingTime;
        var spellRange      = spellinfo.RangeInFt;
        var castingTrait    = spellinfo.CastingTrait;
        var verbal          = spellinfo.Verbal;
        var somatic         = spellinfo.Somatic;
        var component       = spellinfo.Component;
        var ritual          = spellinfo.Ritual;
        var duration        = spellinfo.Duration;
        var attackRoll      = spellinfo.AttackRoll;
        var dmg             = spellinfo.Dmg;
        var desc            = spellinfo.Description;
        var effect          = spellinfo.Effects;
        var classesCanUse   = spellinfo.ClassesCanUse;
        var atHigherLevels  = spellinfo.HigherLevel;
        console.log(name, spellLevel, spellSchool, castingTime, spellRange, castingTrait, verbal, somatic, component, ritual, duration, attackRoll, dmg, desc, effect, classesCanUse, atHigherLevels );
        //console.log(property)
        //Spell Name
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Name');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(name);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Spell Level
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Level');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(spellLevel);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Spell School
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell School');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(spellSchool);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Casting Time
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Casting Time');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(castingTime);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Spell Range
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Range');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(spellRange);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Casting Trait
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Casting Traits');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(castingTrait);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Verbal
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Components: Verbal');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(verbal);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Somatic
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Components: Somatic');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(somatic);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Component
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Components: Material');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(component);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //ritual
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Use as Ritual');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(ritual);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Duration
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Duration');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(duration);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Attack Roll
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Attack Roll (if neccessary)');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(attackRoll);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Dmg
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Damage');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(dmg);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Desc
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Description (Might be same as Spell Effect)');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(desc);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Effect
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Spell Effect (Might be same as Description)');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(effect);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //Classes Can Use
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('Classes That Can Use');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(classesCanUse);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
        //At Higher Levels
        var dt = document.createElement('dt');
        var ttext = document.createTextNode('At Higher Levels (if applicable)');
        dt.appendChild(ttext);
        var dd = document.createElement('dd');
        var dtext = document.createTextNode(atHigherLevels);
        dd.appendChild(dtext);
        dl.appendChild(dt);
        dl.appendChild(dd);
    });
}

//From https://www.w3resource.com/javascript-exercises/javascript-string-exercise-9.php
function capitalize_Words(str)
{
 return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}
