let translate = function () { //闭包
    let Name2NUM = {
        "北京": 0,
        "衡水": 1,
        "保定": 2,
        "天津": 3,
        "德州": 4,
        "济南": 5,
        "青岛": 6,
        "太原": 7,
        "呼和浩特": 8,
        "西安": 9,
    };
    let NUM2Name = ["北京",
        "衡水",
        "保定",
        "天津",
        "德州",
        "济南",
        "青岛",
        "太原",
        "呼和浩特",
        "西安"
    ];

    function getNUM(arg) {
        for (let index in Name2NUM) {
            if (arg == index) {
                return Name2NUM[index];
            }
        }
    }

    function getName(arg) {
        return NUM2Name[arg];
    }
    
    return function (arg) {
        if (typeof arg === "string") {
            return getNUM(arg);
        } else if(typeof arg === "number") {
            return getName(arg);
        }else{
            return null;
        }
    }
    
    /*
    let for_send = function (obj) {
        obj.start_position = Name2NUM(obj.start_position);
        obj.destination = Name2NUM(obj.destination);
    }

    let for_recv = function(obj) {
        let ptr;
        for(let index =0;index<3;index+=1){
            ptr = obj["plan"][index];
            for(let each in ptr.cities){
                ptr.cities[each]=NUM2Name(ptr.cities[each]);
            }
        }
    }
    */
}();
//test
//console.log(translate("衡水"));
//console.log(translate(1));
