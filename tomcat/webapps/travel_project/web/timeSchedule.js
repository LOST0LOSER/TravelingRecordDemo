let timecounter = null;

timeSchedule = (usersys, resultsys) => {

    let plan = resultsys.plan[resultsys.display_num];
    console.log(plan);

    if (usersys.current_time === null) {
        usersys.initInfo(plan);
    }
    let currentline = 0;
    let max_line = plan.vehicles.length; //利用车次确定路线数组长度
    let end_time = parseInt(plan.end_time, 10);
    let counter_time = parseInt(usersys.current_time, 10); //累计计时变量初始化
    console.log(counter_time,end_time);
    //每秒执行一次
    return setInterval(() => {
        if (currentline === max_line && counter_time === end_time) {
            usersys.current_position = plan.cities[currentline];
            usersys.vehicle = null;
            usersys.vehicle_name = null;
            stop();
            alert("到达目的地");
            //恢复改变行程按钮
            document.getElementsByTagName("changeTrip")[3].disabled=false;
        } else {
            while (counter_time === parseInt(plan.cities_times[currentline], 10) && currentline < max_line) {
                usersys.changeInfo(plan, currentline);
                currentline++;
            }
            setTimeout(null, 1000);
            if (counter_time < end_time) {
                counter_time++;
                usersys.current_time = (counter_time % 24).toString() + ":00";
            }
        }
    }, 1000);
}

function set_btn(tag_name,bool) {
    let tmp=document.getElementsByTagName(tag_name);
    if(tmp[0]===(null||undefined)){
        tmp = document.getElementsByName(tag_name);
    }
    for (let index = 0; index < tmp.length; index++) {
        tmp[index].disabled = (!bool);
    }
}

function start_time_counter() {
    if (!timecounter) {
        timecounter = timeSchedule(vm_userSpace, vm_result);
        //setTimeout(clearInterval(timecounter),parseInt(vm_result.plan[vm_result.display_num].end_time)-parseInt(vm_userSpace.current_time);
    }
}

function runTrip() {
    set_btn("button",false);//disabled
    set_btn("changeTrip",true);//true
    //let tmp = document.getElementsByTagName("changeTrip");
    //tmp[tmp.length - 1].disabled = true; //改变行程关闭
    start_time_counter();
    //tmp[tmp.length - 1].disabled = false; //改变行程开
}

function stopTrip() {
    stop();
    set_btn("changeTrip",true);
    tmp[0].disabled=true;
}

function stop() {
    if (timecounter) {
        clearInterval(timecounter);
        timecounter = null;
    }
}

function userclear() {
    stop();
    //set_btn("changeTrip",true);//set btn free
    set_btn("button",true)
    //vm_userSpace.deleteData();
}


/*
if (window.onclick) {
    clearInterval(timecounter);
    timecounter = null;
}*/