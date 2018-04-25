/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
function fc_cursor() {
    document.getElementById('time').value = "";
}
function bl_cursor() {
    if (document.getElementById('time').value === "") {
      document.getElementById('time').value = document.getParameter("time");
    }
}
function fc_cursor1() {
    document.getElementById('name').value = "";
}
function bl_cursor1() {
    if (document.getElementById('name').value === "") {
      document.getElementById('name').value = document.getParameter("name");
    }
}
function fc_cursor2() {
    document.getElementById('desc').value = "";
}
function bl_cursor2() {
    if (document.getElementById('desc').value === "") {
      document.getElementById('desc').value = document.getParameter("desc");
    }
}
function fc_cursor3() {
    document.getElementById('conc').value = "";
}
function bl_cursor3() {
    if (document.getElementById('conc').value === "") {
      document.getElementById('conc').value = document.getParameter("conc");
    }
    
}
function del() {

    confirm("Вы действительно,хотите удалить?");
}
function er(){
   /* var now = new Date();
   obj=document.getElementById('time').value; 
    if(obj<now.getTime())
    {
        alert('Прошедшее время!');
    }else*/
     if ((document.getElementById('name').value === "")||(document.getElementById('time').value === "")||(document.getElementById('descr').value === "")||(document.getElementById('conc').value === "")){
         alert('Все поля должны быть заполнены!');
     }

}

