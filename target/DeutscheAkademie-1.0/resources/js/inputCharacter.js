/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addGermanCharacter(c)
{
      var text = document.getElementById('form:germanWord');
      text.value = (text.value + c.value);        
}

function editFormGermanCharacter(c)
{
      var O = document.getElementById('option');
      var o = O.options[O.selectedIndex].value;
      
      var field = document.getElementById("edit-form:"+ o);
      field.value = (field.value + c.value);
}

function testGermanCharacter(c)
{
    var listValue = document.getElementById("listbox");
    var o = listValue.options[listValue.selectedIndex].value;
    var i = document.getElementById("form:table:" + o + ":german");
  
    i.value=(i.value + c.value);
}

