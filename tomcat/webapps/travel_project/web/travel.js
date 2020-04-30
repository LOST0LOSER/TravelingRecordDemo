//import { saveAs } from "./FileSaver";

//ajax
let xhr = new XMLHttpRequest();

function httpPost(jsonToBePost) { //异步请求
    let url = "/travelServlet";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    //回调函数
    xhr.onreadystatechange = function () {
        //只能在异步请求接受数据后修改数据
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            //更新信息
            let save_data = JSON.parse(xhr.response);
            console.log(save_data);
            //console.log(save_data);
            update(save_data);
            /*
            if (!timecounter) {
                timecounter = timeSchedule(vm_userSpace, vm_result);
                //setTimeout(clearInterval(timecounter),parseInt(vm_result.plan[vm_result.display_num].end_time)-parseInt(vm_userSpace.current_time);
            }
            */
        } else if (xhr.readyState == XMLHttpRequest.LOADING && xhr.status == 500) {
            if (vm_submitSystem.start_position !== null &&
                vm_submitSystem.destination != null &&
                vm_submitSystem.start_time != null) {
                alert("该时段限定时间过于小，无班次,请重新输入");
            } else {
                alert("输入错误");
            }
        }
    }
    xhr.send(JSON.stringify(jsonToBePost));
}
/*
function httpGet() {
    let xhr = new XMLHttpRequest();
    let url = "/travel";
    let receiveJson;
    xhr.open("get", url, true);
    xhr.setRequestHeader("Contenet-type", "application/json");
    xhr.onload = function () {
        console.log(xhr.response);
        receiveJson = JSON.parse(xhr.response);
    }
    xhr.send(null);
    console.log(receiveJson);
    return receiveJson;
}
*/
function update(data) {
    vm_result.getData(data);
    //console.log(vm_result.plan);//检查方案数
}

function clearAll() {
    vm_submitSystem.deleteData();
    vm_userSpace.deleteData();
    vm_result.deleteData();
    userclear();
}

//方案模板
Vue.component('muti-tab', {
    props: ["ptr"],
    template: '\
    <div>\
        <div class="plan" style="justify-content:center">\
        <span>起始时间:</span>\
        <input type="tel" v-model="ptr.display_start_time" readonly></input>\
        <span>到达时间:</span>\
        <input type="tel" v-model="ptr.display_end_time" readonly></input>\
        <span>途经城市:</span>\
        <cities :city="city" :key="city.id" v-for="(city,index) in ptr.cities"></cities>\
        </div>\
        <!--\
        <span>交通工具:</span>\
        <input v-model="ptr.vehicle" readonly></input>\
        <input v-model="ptr.cities" readonly></input>\
        -->\
    </div>'
});

Vue.component('cities', {
    props: ["city"],
    template: '<input type="tel" v-model=city class="city" readonly></input>'
});

//模块实例
let vm_submitSystem = new Vue({
    el: "#submitSystem",
    data: {
        start_position: null,
        destination: null,
        start_time: "00:00",
        limited_time: "00:00"
    },
    methods: {
        submit: function () {
            //先转译
            if (this.start_position !== this.destination && this.start_position && this.destination) {
                let send = {};
                send.start_time = this.start_time;
                send.start_position = translate(this.start_position);
                send.destination = translate(this.destination);
                send.limited_time = this.limited_time;
                httpPost(send);
            } else {
                alert("输入错误");
            }
        },
        deleteData: function () {
            this.start_position = null;
            this.destination = null;
            this.start_time = "";
            this.limited_time = "";
        }
    }
})

let vm_userSpace = new Vue({
    el: "#userspace",
    data: {
        current_position: null,
        current_time: null,
        costs: null,
        left_money: null,
        vehicle: null,
        vehicle_name:null,
        new_position: null,
        new_limit_time: null
    },
    methods: {
        getData: function (obj) {
            this.current_position = translate(obj.current_position);
            this.current_time = obj.current_time;
            this.costs = obj.costs;
            this.left_money = obj.left_money;
        },
        deleteData: function () {
            this.current_position = null;
            this.current_time = null;
            this.costs = null;
            this.left_money = null;
            this.vehicle = null;
            this.vehicle_name = null;
            this.new_position = null;
            this.new_limit_time = null;
        },
        changeInfo: function (plan, index) {
            this.current_position = plan.cities[index];
            this.vehicle = plan.vehicles[index];
            this.vehicle_name = plan.vehicle_names[index];
            this.costs += plan.moneys[index];
            this.left_money -= plan.moneys[index];
            //this.current_time = plan.cities_times[index+];
        },
        initInfo: function (plan) {
            this.current_position = plan.cities[0];
            this.vehicle = plan.vehicles[0];
            this.vehicle = plan.vehicles[0];
            this.costs = 0;
            if(!this.left_money){
                this.left_money = 999;
            }
            this.current_time = plan.cities_times[0];
        },
        changeTrip: function () {
            let send = {};
            if (this.new_position !== null && this.new_position !== this.current_position) {
                send.destination = translate(this.new_position);
                send.start_position = translate(this.current_position);
                send.start_time = this.current_time;
                send.limited_time = this.new_limit_time;
                //console.log(send);
                httpPost(send);
                set_btn("changeTrip",true);
                /*setTimeout(function () {
                    start_time_counter();
                }, 1000);
                */
            } else {
                alert("输入错误");
            }
        }
    }
});

let vm_result = new Vue({
    el: "#result",
    data: {
        //定义切换方案按钮
        tabs: ["最短时间方案", "最少消费方案", "限定时间最少消费方案"],
        display_num: 0,
        //方案数据
        plan: [{
                start_time: null,
                end_time: null,
                vehicles: null,
                vehicle_names:null,
                cities: null,
                cities_times: null,
                moneys: null,
                display_start_time: null,
                display_end_time: null
            },
            {
                start_time: null,
                end_time: null,
                vehicles: null,
                vehicle_names:null,
                cities: null,
                cities_times: null,
                moneys: null,
                display_start_time: null,
                display_end_time: null
            },
            {
                start_time: null,
                end_time: null,
                vehicles: null,
                vehicle_names:null,
                cities: null,
                cities_times: null,
                moneys: null,
                display_start_time: null,
                display_end_time: null
            }
        ]
    },
    methods: {
        convert_display_time: function (time) {
            let tmptime = parseInt(time,10);
            if (tmptime < 24) {
                return tmptime + ":00";
            } else {
                return parseInt(tmptime/24,10).toString() + "天后 " + parseInt(tmptime % 24,10).toString() + ":00";
            }
        },
        choose_tab: function (index) {
            this.display_num = index;
        },
        getData: function (obj) {
            //浅拷贝
            let self;
            let ptr;
            for (let index = 0; index < 3; index += 1) {
                self = this.plan[index];
                ptr = obj.plan[index];
                //console.log(ptr);
                for (let i = 0; i < ptr.cities.length; i += 1) {
                    ptr.cities[i] = translate(ptr.cities[i]);
                }
                self.start_time = ptr.start_time;
                self.end_time = ptr.end_time;
                self.display_start_time=this.convert_display_time(self.start_time);
                self.display_end_time=this.convert_display_time(self.end_time);
                self.vehicles = ptr.vehicles;
                self.vehicle_names = ptr.vehicle_names;
                self.cities = ptr.cities;
                self.cities_times = ptr.cities_times;
                self.moneys = ptr.moneys;
            }
        },
        /*
        changeData_alpha:function(obj){
            let self = this.plan
        },*/
        deleteData: function () {
            let self = this;
            for (let index = 0; index < 3; index += 1) {
                self = this.plan[index];
                self.start_time = null;
                self.end_time = null;
                self.cities = null;
                self.vehicles = null;
                self.vehicle_names = null;
                self.cities_times = null;
                self.money = null;
                self.display_start_time = null;
                self.display_end_time = null;
            }
        }
    }
})