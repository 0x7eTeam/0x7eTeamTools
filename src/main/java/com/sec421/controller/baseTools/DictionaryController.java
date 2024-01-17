package com.sec421.controller.baseTools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
public class DictionaryController {
    public static final String HTML_CONTENT = "<!DOCTYPE html>\n" +
            "<html lang=\"zh\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <style>\n" +
            "        html, body {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        body {\n" +
            "            padding-bottom: 24px;\n" +
            "        }\n" +
            "        label {\n" +
            "            width: 100px;\n" +
            "            display: inline-block;\n" +
            "            text-align: right;\n" +
            "            font-size: 14px;\n" +
            "            color: #495060;\n" +
            "            padding: 10px 12px 10px 0;\n" +
            "        }\n" +
            "        label:empty {\n" +
            "            width: 0;\n" +
            "        }\n" +
            "        input {\n" +
            "            width: 200px;\n" +
            "            height: 24px;\n" +
            "            padding: 2px 6px;\n" +
            "            font-size: 12px;\n" +
            "            border: 1px solid #dddee1;\n" +
            "            border-radius: 4px;\n" +
            "        }\n" +
            "        input:hover,input:focus {\n" +
            "            border-color: #57a3f3;\n" +
            "            box-shadow: 0 0 0 2px #a0c3e7;\n" +
            "        }\n" +
            "        input:focus {\n" +
            "            outline: none;\n" +
            "        }\n" +
            "        [type='checkbox'] {\n" +
            "            display: none;\n" +
            "        }\n" +
            "        .switch {\n" +
            "            width: 40px;\n" +
            "            height: 20px;\n" +
            "            background: #F1F1F1;\n" +
            "            display: inline-block;\n" +
            "            border-radius: 20px;\n" +
            "            transition: background 0.4s;\n" +
            "            padding: 0;\n" +
            "            vertical-align: middle;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "        .switch > .ball {\n" +
            "            display: block;\n" +
            "            width: 16px;\n" +
            "            height: 16px;\n" +
            "            border-radius: 16px;\n" +
            "            background: #E9E9E9;\n" +
            "            border: 2px solid #d5d0d0;\n" +
            "            transition: all 0.4s;\n" +
            "\n" +
            "        }\n" +
            "        [type='checkbox']:checked + label {\n" +
            "            background: #2d8cf0;\n" +
            "        }\n" +
            "        [type='checkbox']:checked + label > .ball {\n" +
            "            margin-left: 20px;\n" +
            "        }\n" +
            "        [type='checkbox']:active + label > .ball  {\n" +
            "            width: 30px;\n" +
            "            margin-left: 10px;\n" +
            "        }\n" +
            "        input[type='button'] {\n" +
            "            background-color: #57a3f3;\n" +
            "            color: white;\n" +
            "            border: 1px solid #e4e5e7;\n" +
            "            box-shadow: unset;\n" +
            "            cursor: pointer;\n" +
            "            padding: 0 10px;\n" +
            "            height: 35px;\n" +
            "            font-size: 14px;\n" +
            "        }\n" +
            "        .alert-info {\n" +
            "            background-color: rgb(232,243,255);\n" +
            "            border: 1px solid transparent;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            justify-content: flex-start;\n" +
            "            padding: 8px 15px;\n" +
            "            margin: 10px 48px;\n" +
            "            width: 800px;\n" +
            "            font-size: 14px;\n" +
            "        }\n" +
            "        .alert-icon {\n" +
            "            width: 1.1em;\n" +
            "            margin: 0 8px 0 0;\n" +
            "            color: rgb(22,93,255);\n" +
            "            vertical-align: middle;\n" +
            "        }\n" +
            "        .loading {\n" +
            "            position: fixed;\n" +
            "            top: 0;\n" +
            "            left: 0;\n" +
            "            width: 100vw;\n" +
            "            height: 100vh;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            justify-content: center;\n" +
            "            padding-top: 0;\n" +
            "            background-color: rgba(0, 0, 0, 0.4);\n" +
            "        }\n" +
            "        .loading-bg {\n" +
            "            border-radius: 50%;\n" +
            "            border: 5px solid #d5d0d0;\n" +
            "            border-left: 5px solid rgb(22, 93, 255);\n" +
            "            animation: load 1.5s linear infinite;\n" +
            "            width: 50px;\n" +
            "            height: 50px;\n" +
            "        }\n" +
            "\n" +
            "        @keyframes load {\n" +
            "            from {\n" +
            "                transform: rotate(0deg);\n" +
            "            }\n" +
            "            to {\n" +
            "                transform: rotate(360deg);\n" +
            "            }\n" +
            "        }\n" +
            "        .nav-item {\n" +
            "            height: 100%;\n" +
            "            cursor: pointer;\n" +
            "            z-index: 1;\n" +
            "        }\n" +
            "        .nav-item>:first-child {\n" +
            "            line-height: 50px;\n" +
            "            padding: 0 15px;\n" +
            "        }\n" +
            "        .nav-item a {\n" +
            "            text-align: center;\n" +
            "            color: unset;\n" +
            "            text-decoration: unset;\n" +
            "        }\n" +
            "        .nav-item:hover, .nav-item .nav-item-box li:hover {\n" +
            "            background-color: #57a3f3;\n" +
            "        }\n" +
            "        .nav-item .nav-item-box {\n" +
            "            max-height: 0;\n" +
            "            transition: max-height 0.5s;\n" +
            "            overflow: hidden;\n" +
            "        }\n" +
            "        .nav-item:hover .nav-item-box {\n" +
            "           max-height: 2000px;\n" +
            "        }\n" +
            "        .nav-item .nav-item-box ul {\n" +
            "            background-color: #495060;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        .nav-item .nav-item-box li {\n" +
            "            padding: 5px;\n" +
            "        }\n" +
            "    </style>\n" +
            "    <script>\n" +
            "        function reset() {\n" +
            "            document.getElementById('first_name').value = '';\n" +
            "            document.getElementById('second_name').value = '';\n" +
            "            document.getElementById('third_name').value = '';\n" +
            "            document.getElementById('birthday').value = '';\n" +
            "            document.getElementById('birthday2').value = '';\n" +
            "            document.getElementById('email').value = '';\n" +
            "            document.getElementById('mobile').value = '';\n" +
            "            document.getElementById('telephone').value = '';\n" +
            "            document.getElementById('username').value = '';\n" +
            "            document.getElementById('account').value = '';\n" +
            "            document.getElementById('organization').value = '';\n" +
            "            document.getElementById('company').value = '';\n" +
            "            document.getElementById('like_use').value = '';\n" +
            "            document.getElementById('id_card').value = '';\n" +
            "            document.getElementById('connector').value = '';\n" +
            "            document.getElementById('connector_left').checked = false;\n" +
            "            document.getElementById('connector_middle').checked = false;\n" +
            "            document.getElementById('connector_right').checked = false;\n" +
            "            document.getElementById('common').value = '123,888,666,000,111,aaa,abc,qaz,qwe,asd,zxc,!@#,1234,1qaz,qwer,asdf,zxcv,!@#$,1357,2468,0123,6789,6666,8888,12345,123456';\n" +
            "            document.getElementById('have_year').checked = false;\n" +
            "            document.getElementById('year').value = '';\n" +
            "            document.getElementById('number_filter').checked = false;\n" +
            "            document.getElementById('string_filter').checked = false;\n" +
            "            document.getElementById('long').value = 16;\n" +
            "            document.getElementById('short').value = 6;\n" +
            "            document.getElementById('capitalize').checked = true;\n" +
            "            document.getElementById('lowercase').checked = false;\n" +
            "            document.getElementById('uppercase').checked = false;\n" +
            "            document.getElementById('result').style.display='none';\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 得到字符串大写,包含原文,去空\n" +
            "         * @param l list,需要处理的字符串列表\n" +
            "         */\n" +
            "        function get_upper(l) {\n" +
            "            const upperPattern = new RegExp(\".*[a-z].*\");\n" +
            "            return l.concat(l.map(i=>upperPattern.test(i)?i.toUpperCase():'')).filter(Boolean);\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 得到字符串小写,包含原文,去空\n" +
            "         * @param l list,需要处理的字符串列表\n" +
            "         */\n" +
            "        function get_lower(l) {\n" +
            "            const lowerPattern = new RegExp(\".*[A-Z].*\");\n" +
            "            return l.concat(l.map(i=>lowerPattern.test(i)?i.toLowerCase():'')).filter(Boolean);\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 首字母大写,包含原文,去空\n" +
            "         * @param l list,需要处理的字符串列表\n" +
            "         */\n" +
            "        function get_capitalize(l) {\n" +
            "            const capitalizePattern = new RegExp(\"^[a-z].*\");\n" +
            "            return l.concat(l.map(i=>capitalizePattern.test(i)?i.replace(/( |^)[a-z]/g, (L) => L.toUpperCase()):'')).filter(Boolean);\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 去重去空后的列表\n" +
            "         * @param l list,需要处理的字符串列表\n" +
            "         */\n" +
            "        function get_distinct_list(l) {\n" +
            "            return Array.from(new Set(l)).filter(Boolean);\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 小于等于x位自动重复, 返回原文及重复后的列表组合,去空\n" +
            "         * @param l list,需要重复的列表\n" +
            "         * @param x int,小于等于此长度将自动重复\n" +
            "         */\n" +
            "        function get_repeat(l, x) {\n" +
            "            x = (typeof x === 'undefined') ? 3: x;\n" +
            "            const lr = l.map(i => (i.length > 0 && i.length <= x) ? i+i: '');\n" +
            "            return l.concat(lr).filter(Boolean);\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 取密码前几位及后几位,包含原文,去空\n" +
            "         * @param s str, 需要处理的字符串\n" +
            "         * @param l int, 需要的长度,可输入任意数量\n" +
            "         */\n" +
            "        function get_head_tail(s, ...l) {\n" +
            "            return [s].concat(l.map(i=>s.length>i?[s.substring(0,i), s.substring(s.length-i)]:[]).reduce((a, b) => a.concat(b))).filter(Boolean);\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 列表去掉过长和过短\n" +
            "         * @param l list, 需要处理的字符串列表\n" +
            "         * @param start 最小长度\n" +
            "         * @param end 最大长度\n" +
            "         */\n" +
            "        function drop_short_long(l, start, end) {\n" +
            "            start = (typeof start === 'undefined') ? 6: start;\n" +
            "            end = (typeof end === 'undefined') ? 16: end;\n" +
            "            return l.filter(i=>((start ? i.length>=start : true) && (end ? i.length<=end : true)));\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 去掉纯字母或纯数字\n" +
            "         * @param l 需要处理的字符串列表\n" +
            "         * @param rtype 可选str或int\n" +
            "         */\n" +
            "        function drop_string_int(l, rtype) {\n" +
            "            if (['str', 'int'].indexOf(rtype) < 0) {\n" +
            "                return l;\n" +
            "            }\n" +
            "            const pattern = rtype === 'str' ? /^[a-zA-Z]*$/ : /^[0-9]*$/;\n" +
            "            return l.filter(i=>!pattern.test(i));\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 计算array的笛卡尔积，相当于python中的itertools.product\n" +
            "         * calc_descartes([['2019', '2020'], ['白色', '灰色', '蓝色']]) => ['2019白色', '2019灰色', '2019蓝色', '2020白色', '2020灰色', '2020蓝色']\n" +
            "         * @param array\n" +
            "         */\n" +
            "        function calc_descartes(array) {\n" +
            "            if (array.length < 2) return array[0] || [];\n" +
            "            return array.reduce((total, currentValue) => {\n" +
            "                let res = [];\n" +
            "                total.forEach(t => {\n" +
            "                    currentValue.forEach(cv => {\n" +
            "                        res.push([t, cv]);\n" +
            "                    })\n" +
            "                })\n" +
            "                return res;\n" +
            "            })\n" +
            "        }\n" +
            "\n" +
            "        /**\n" +
            "         * 数组排列组合，等于python中的itertools.permutations，permutations([1,2,3,4], 2) => [[1,2],[1,3],[1,4],[2,1],[2,3],[2,4],[3,1],[3,2],[3,4],[4,1],[4,2],[4,3]]\n" +
            "         * @param arr 源数组\n" +
            "         * @param size 选取元素的个数\n" +
            "         */\n" +
            "        function permutations(arr, size) {\n" +
            "            //定义数组保存结果\n" +
            "            let result = [];\n" +
            "\n" +
            "            //selected数组包含已经选中的元素\n" +
            "            //arr数组包含未选中元素数组，size表示还需选取元素的个数\n" +
            "            function _combine(selected, arr, size) {\n" +
            "                //如果size===0，则一次组合完成，存入result数组并返回\n" +
            "                if (size === 0) {\n" +
            "                    result.push(selected);\n" +
            "                    return;\n" +
            "                }\n" +
            "                //遍历所有可能选中的元素，遍历的次数为数组长度减去(size-1)\n" +
            "                for (let i = 0; i < arr.length; i++) {\n" +
            "                    //复制数组，避免对selected数组数据的更改\n" +
            "                    let temp = selected.slice();\n" +
            "                    temp.push(arr[i]);\n" +
            "                    _combine(temp, arr.slice(0, i).concat(arr.slice(i + 1)), size - 1);\n" +
            "                }\n" +
            "            }\n" +
            "            _combine([], arr, size);\n" +
            "            return result;\n" +
            "        }\n" +
            "        function show_loading() {\n" +
            "            const loading = document.getElementById('loading');\n" +
            "            loading.style.display = 'block';\n" +
            "        }\n" +
            "        function hide_loading() {\n" +
            "            const loading = document.getElementById('loading');\n" +
            "            loading.style.display = 'none';\n" +
            "        }\n" +
            "\n" +
            "        function main() {\n" +
            "            show_loading();\n" +
            "            setTimeout(() => {\n" +
            "                const start_time = new Date().getTime();\n" +
            "                const pass_list = generate();\n" +
            "\n" +
            "                const pass_first_length = document.getElementById('pass_first_length');\n" +
            "                const pass_first_text = document.getElementById('pass_first_text');\n" +
            "                const pass_second_length = document.getElementById('pass_second_length');\n" +
            "                const pass_second_text = document.getElementById('pass_second_text');\n" +
            "                const pass_third_length = document.getElementById('pass_third_length');\n" +
            "                const pass_third_text = document.getElementById('pass_third_text');\n" +
            "                const pass_all_length = document.getElementById('pass_all_length');\n" +
            "                const pass_all_text = document.getElementById('pass_all_text');\n" +
            "                const result = document.getElementById('result');\n" +
            "                pass_first_text.value=pass_list[0].join('\\n');\n" +
            "                pass_first_text.rows=pass_list[0].length;\n" +
            "                pass_first_length.innerText=pass_list[0].length.toString();\n" +
            "                pass_second_text.value=pass_list[1].join('\\n');\n" +
            "                pass_second_text.rows=pass_list[1].length;\n" +
            "                pass_second_length.innerText=pass_list[1].length.toString();\n" +
            "                pass_third_text.value=pass_list[2].join('\\n');\n" +
            "                pass_third_text.rows=pass_list[2].length;\n" +
            "                pass_third_length.innerText=pass_list[2].length.toString();\n" +
            "                let pass_all = pass_list.flat();\n" +
            "                pass_all_text.value=pass_all.join('\\n');\n" +
            "                pass_all_text.rows=pass_all.length;\n" +
            "                pass_all_length.innerText=pass_all.length.toString();\n" +
            "                result.style.display='flex';\n" +
            "\n" +
            "                const end_time = new Date().getTime();\n" +
            "                console.log(`cost ${end_time - start_time}ms`)\n" +
            "\n" +
            "                hide_loading();\n" +
            "            }, 0);\n" +
            "        }\n" +
            "\n" +
            "        function download() {\n" +
            "            const list = generate().flat();\n" +
            "            console.log(list.length);\n" +
            "\n" +
            "            const ele = document.createElement('a');\n" +
            "            ele.download = \"passwords.txt\";\n" +
            "            ele.style.display = \"none\";\n" +
            "\n" +
            "            const blob = new Blob([list.join(\"\\n\")]);\n" +
            "            ele.href = URL.createObjectURL(blob);\n" +
            "            document.body.appendChild(ele);\n" +
            "            ele.click();\n" +
            "            document.body.removeChild(ele);\n" +
            "        }\n" +
            "\n" +
            "        function generate() {\n" +
            "            const first_name = document.getElementById('first_name')['value'];\n" +
            "            const second_name = document.getElementById('second_name')['value'];\n" +
            "            const third_name = document.getElementById('third_name')['value'];\n" +
            "            const birthday = document.getElementById('birthday')['value'];\n" +
            "            const birthday2 = document.getElementById('birthday2')['value'];\n" +
            "            const email = document.getElementById('email')['value'];\n" +
            "            const mobile = document.getElementById('mobile')['value'];\n" +
            "            const telephone = document.getElementById('telephone')['value'];\n" +
            "            const username = document.getElementById('username')['value'];\n" +
            "            const account = document.getElementById('account')['value'];\n" +
            "            const organization = document.getElementById('organization')['value'];\n" +
            "            const company = document.getElementById('company')['value'];\n" +
            "            const like_use = document.getElementById('like_use')['value'];\n" +
            "            const id_card = document.getElementById('id_card')['value'];\n" +
            "            const work_no = document.getElementById('work_no')['value'];\n" +
            "            const connector = document.getElementById('connector')['value'].split('');\n" +
            "            const connector_left = document.getElementById('connector_left')['checked'];\n" +
            "            const connector_middle = document.getElementById('connector_middle')['checked'];\n" +
            "            const connector_right = document.getElementById('connector_right')['checked'];\n" +
            "            const common = document.getElementById('common')['value'];\n" +
            "            const have_year = document.getElementById('have_year')['checked'];\n" +
            "            const year = parseInt(document.getElementById('year')['value']);\n" +
            "            const number_filter = document.getElementById('number_filter')['checked'];\n" +
            "            const string_filter = document.getElementById('string_filter')['checked'];\n" +
            "            const long = parseInt(document.getElementById('long')['value']);\n" +
            "            const short = parseInt(document.getElementById('short')['value']);\n" +
            "            const capitalize = document.getElementById('capitalize')['checked'];\n" +
            "            const lowercase = document.getElementById('lowercase')['checked'];\n" +
            "            const uppercase = document.getElementById('uppercase')['checked'];\n" +
            "\n" +
            "            const first_name_combine = /^[a-zA-Z0-9]+$/.test(first_name) ? get_repeat([first_name], 3) : [''];\n" +
            "            const last_name_combine = /^[a-zA-Z0-9]+$/.test(second_name + third_name) ? get_repeat([second_name + third_name], 3) : [''];\n" +
            "            let name_all = [first_name_combine[0] + last_name_combine[0], last_name_combine[0] + first_name_combine[0]];\n" +
            "            const last_name_a_b = second_name.substring(0, 1) + third_name.substring(0, 1);\n" +
            "            name_all = name_all.concat([first_name.substring(0, 1) + last_name_a_b, first_name_combine[0] + last_name_a_b, last_name_a_b + first_name.substring(0, 1), last_name_a_b + first_name_combine[0], first_name.substring(0, 1) + second_name + third_name, second_name + third_name, first_name]);\n" +
            "            name_all = name_all.concat(get_repeat(get_head_tail(username, 3, 4))).concat(get_repeat(get_head_tail(account, 3, 4)))\n" +
            "            name_all = get_distinct_list(name_all);\n" +
            "            console.log('name_all', name_all);\n" +
            "\n" +
            "            let birthday_all = [];\n" +
            "            let b = birthday.replaceAll('-', '');\n" +
            "            let b2 = birthday2.replaceAll('-', '');\n" +
            "            birthday_all = birthday_all.concat(get_head_tail(b, 4)).concat(get_head_tail(b2, 4));\n" +
            "            birthday_all = birthday_all.concat(b.substring(4, 5) === '0' ? [b.substring(5, 8), b.substring(5, 8) + b.substring(5, 8)] : []);\n" +
            "            birthday_all = birthday_all.concat(b2.substring(4, 5) === '0' ? [b2.substring(5, 8), b2.substring(5, 8) + b2.substring(5, 8)] : []);\n" +
            "            birthday_all = get_distinct_list(birthday_all);\n" +
            "            console.log('birthday_all', birthday_all);\n" +
            "\n" +
            "            const email_all = get_distinct_list([email].concat(get_repeat(get_head_tail(email.split('@')[0], 3, 4), 3)));\n" +
            "            console.log('email_all', email_all);\n" +
            "\n" +
            "            let phone_all = get_distinct_list(get_repeat(get_head_tail(mobile, 3, 4, 5, 6)).concat(get_repeat(get_head_tail(telephone, 3, 4, 5, 6))));\n" +
            "            console.log('phone_all', phone_all);\n" +
            "\n" +
            "            let id_card_all = id_card.length > 0 ? get_distinct_list(get_repeat(get_head_tail(id_card, 3, 4, 6, 8).concat(get_head_tail(id_card.substring(0, id_card.length - 1), 3, 4, 6, 8).slice(1)))) : [];\n" +
            "            console.log('id_card_all', id_card_all);\n" +
            "\n" +
            "            let work_no_all = get_distinct_list(get_repeat(get_head_tail(work_no, 3, 4, 6, 8)));\n" +
            "            console.log('work_no_all', work_no_all);\n" +
            "\n" +
            "            let org_all = get_distinct_list([organization, company].map(i => get_repeat(get_head_tail(i, 3, 4))).reduce((a, b) => a.concat(b)));\n" +
            "            console.log('org_all', org_all);\n" +
            "\n" +
            "            let like_all = get_distinct_list(like_use.split(',').map(i => get_repeat(get_head_tail(i, 3, 4))).reduce((a, b) => a.concat(b)));\n" +
            "            console.log('like_all', like_all);\n" +
            "\n" +
            "            let common_all = common.split(',');\n" +
            "            if (have_year) {\n" +
            "                Array.from(Array(year), (el, i) => common_all.push((new Date().getFullYear() - year + i).toString()));\n" +
            "            }\n" +
            "            console.log('common_all', common_all);\n" +
            "\n" +
            "            let pass_list_all = [name_all, birthday_all, email_all, phone_all, id_card_all, work_no_all, org_all, like_all, common_all];\n" +
            "            let pass_first = pass_list_all.reduce((a, b) => a.concat(b));\n" +
            "            console.log('pass_first', pass_first);\n" +
            "\n" +
            "            let pass_second = [];\n" +
            "            let pass_third = [];\n" +
            "\n" +
            "            permutations(pass_list_all, 2).forEach(passArr => {\n" +
            "                let pass_combine = calc_descartes(passArr);\n" +
            "                pass_second = pass_second.concat(pass_combine.map(p=>p[0]+p[1]));\n" +
            "                pass_combine.forEach(p=>{\n" +
            "                    connector.forEach(c=>{\n" +
            "                    if (connector_left) {\n" +
            "                        pass_third.push(c+p[0]+p[1]);\n" +
            "                    }\n" +
            "                    if (connector_middle) {\n" +
            "                        pass_third.push(p[0]+c+p[1]);\n" +
            "                    }\n" +
            "                    if (connector_right) {\n" +
            "                        pass_third.push(p[0]+p[1]+c);\n" +
            "                    }\n" +
            "                    });\n" +
            "                });\n" +
            "            });\n" +
            "\n" +
            "            let pass_list = [pass_first, pass_second, pass_third];\n" +
            "            pass_list = pass_list.map(i => drop_short_long(i, short, long));\n" +
            "            if (number_filter) {\n" +
            "                pass_list = pass_list.map(i => drop_string_int(i, 'int'));\n" +
            "            }\n" +
            "            if (string_filter) {\n" +
            "                pass_list = pass_list.map(i => drop_string_int(i, 'str'));\n" +
            "            }\n" +
            "            if (capitalize) {\n" +
            "                pass_list = pass_list.map(i => get_capitalize(i));\n" +
            "            }\n" +
            "            if (lowercase) {\n" +
            "                pass_list = pass_list.map(i => get_lower(i));\n" +
            "            }\n" +
            "            if (uppercase) {\n" +
            "                pass_list = pass_list.map(i => get_upper(i));\n" +
            "            }\n" +
            "            pass_list = pass_list.map(i => get_distinct_list(i));\n" +
            "            return pass_list;\n" +
            "        }\n" +
            "\n" +
            "        function copy(cid) {\n" +
            "            const textNode = document.getElementById(cid);\n" +
            "            textNode.select();\n" +
            "            document.execCommand(\"Copy\");\n" +
            "            textNode.blur();\n" +
            "            const notification = document.getElementById('notification');\n" +
            "            notification.style.right = '20px';\n" +
            "            notification.style.visibility = 'visible';\n" +
            "            notification.style.opacity = '1';\n" +
            "            setTimeout(()=>{\n" +
            "                notification.style.right = '-20px';\n" +
            "                notification.style.visibility = 'hidden';\n" +
            "                notification.style.opacity = '0';\n" +
            "            }, 3000)\n" +
            "        }\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div>\n" +
            "    <label for=\"first_name\">姓名</label><input type=\"text\" name=\"first_name\" id=\"first_name\" placeholder=\"请输入姓(英文)\" title=\"请输入姓(英文)\" />\n" +
            "    <label for=\"second_name\"></label><input type=\"text\" name=\"second_name\" id=\"second_name\" placeholder=\"请输入名的第一个字(英文)\" title=\"请输入名的第一个字(英文)\" />\n" +
            "    <label for=\"third_name\"></label><input type=\"text\" name=\"third_name\" id=\"third_name\" placeholder=\"请输入名的第二个字(如果有，英文)\" title=\"请输入名的第二个字(如果有，英文)\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"birthday\">生日</label><input type=\"date\" name=\"birthday\" id=\"birthday\" placeholder=\"公历生日\" title=\"公历生日\" />\n" +
            "    <label for=\"birthday2\"></label><input type=\"date\" name=\"birthday2\" id=\"birthday2\" placeholder=\"农历生日\" title=\"农历生日\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"email\">邮件</label><input type=\"email\" name=\"email\" id=\"email\" placeholder=\"请输入邮箱\" title=\"请输入邮箱\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"mobile\">电话</label><input type=\"tel\" name=\"mobile\" id=\"mobile\" placeholder=\"请输入座机号,不要-号\" title=\"请输入座机号,不要-号\" />\n" +
            "    <label for=\"telephone\"></label><input type=\"tel\" name=\"telephone\" id=\"telephone\" placeholder=\"请输入手机号\" title=\"请输入手机号\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"username\">用户名</label><input type=\"text\" name=\"username\" id=\"username\" placeholder=\"请输入用户名(英文)\" title=\"请输入用户名(英文)\" />\n" +
            "    <label for=\"account\"></label><input type=\"text\" name=\"account\" id=\"account\" placeholder=\"请输入用户账号\" title=\"请输入用户账号\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"organization\">组织</label><input type=\"text\" name=\"organization\" id=\"organization\" placeholder=\"请输入组织名(英文)\" title=\"请输入组织名(英文)\" />\n" +
            "    <label for=\"company\"></label><input type=\"text\" name=\"company\" id=\"company\" placeholder=\"请输入公司名(英文)\" title=\"请输入公司名(英文)\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"like_use\">短语</label><input type=\"text\" name=\"like_use\" id=\"like_use\" style=\"width: 320px;\" placeholder=\"请输入常用短语(英文),如iloveyou,多个用逗号分隔\" title=\"请输入常用短语(英文),如iloveyou,多个用逗号分隔\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"id_card\">身份证号</label><input type=\"text\" name=\"id_card\" id=\"id_card\" placeholder=\"请输入身份证号\" title=\"请输入身份证号\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"work_no\">工号</label><input type=\"text\" name=\"work_no\" id=\"work_no\" placeholder=\"请输入工号\" title=\"请输入工号\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"connector\">连接符</label>\n" +
            "    <label for=\"connector_left\" style=\"width: 30px;padding-right: 5px;\">左</label><input type=\"checkbox\" name=\"connector_left\" id=\"connector_left\" value=\"左\"/><label class=\"switch\" for=\"connector_left\"><span class=\"ball\"></span></label>\n" +
            "    <label for=\"connector_middle\" style=\"width: 30px;padding-right: 5px;\">中</label><input type=\"checkbox\" name=\"connector_middle\" id=\"connector_middle\" value=\"中\"/><label class=\"switch\" for=\"connector_middle\"><span class=\"ball\"></span></label>\n" +
            "    <label for=\"connector_right\" style=\"width: 30px;padding-right: 5px;\">右</label><input type=\"checkbox\" name=\"connector_right\" id=\"connector_right\" value=\"右\"/><label class=\"switch\" for=\"connector_right\"><span class=\"ball\"></span></label>\n" +
            "    <input type=\"text\" name=\"connector\" id=\"connector\" style=\"width: 500px;\" placeholder=\"请输入连接符(如.!_-#@:$&*~?%+=/|),尽量减少,否则会极大增加密码数量\" title=\"请输入连接符(如.!_-#@:$&*~?%+=/|),尽量减少,否则会极大增加密码数量\" />\n" +
            "    <span title=\"连接符用于连接两条信息. 如姓zhang且生日为0229,用@在中间连接则为zhang@0229\" style=\"cursor: help\"><svg class=\"alert-icon\" viewBox=\"0 0 48 48\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" stroke=\"currentColor\" stroke-width=\"4\" stroke-linecap=\"butt\" stroke-linejoin=\"miter\"><path fill-rule=\"evenodd\" clip-rule=\"evenodd\" d=\"M24 44c11.046 0 20-8.954 20-20S35.046 4 24 4 4 12.954 4 24s8.954 20 20 20Zm-3.862-24.021a.461.461 0 0 0 .462-.462 2.37 2.37 0 0 1 .636-1.615C21.64 17.48 22.43 17 23.988 17c1.465 0 2.483.7 3.002 1.493.555.848.446 1.559.182 1.914-.328.444-.736.853-1.228 1.296-.15.135-.335.296-.533.468-.354.308-.75.654-1.067.955C23.22 24.195 22 25.686 22 28v.013a1 1 0 0 0 1.006.993l2.008-.012a.993.993 0 0 0 .986-1c.002-.683.282-1.19 1.101-1.97.276-.262.523-.477.806-.722.21-.18.439-.379.713-.626.57-.513 1.205-1.13 1.767-1.888 1.516-2.047 1.161-4.634-.05-6.485C29.092 14.398 26.825 13 23.988 13c-2.454 0-4.357.794-5.642 2.137-1.25 1.307-1.742 2.954-1.746 4.37 0 .26.21.472.47.472h3.068Zm1.868 14.029a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V32a1 1 0 0 0-1-1h-2a1 1 0 0 0-1 1v2.008Z\" fill=\"currentColor\" stroke=\"none\"></path></svg></span>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"common\">常用词组</label><input type=\"text\" name=\"common\" id=\"common\" style=\"width: 800px;\" value=\"123,888,666,000,111,aaa,abc,qaz,qwe,asd,zxc,!@#,1234,1qaz,qwer,asdf,zxcv,!@#$,1357,2468,0123,6789,6666,8888,12345,123456\" placeholder=\"请输入常用词组,如123456,abcd等，多个用逗号分隔\" title=\"请输入常用词组,如123456,abcd等，多个用逗号分隔\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"have_year\">最近年份</label><input type=\"checkbox\" name=\"have_year\" id=\"have_year\"/><label class=\"switch\" for=\"have_year\"><span class=\"ball\"></span></label>\n" +
            "    <label for=\"year\" style=\"display: none\"></label><input type=\"number\" name=\"year\" id=\"year\" style=\"width: 100px;\" placeholder=\"最近几年的年份,如果要特定年份可以输入到常用词组中\" title=\"最近几年的年份,如果要特定年份可以输入到常用词组中\" value=\"10\"/>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"number_filter\">去掉纯数字</label><input type=\"checkbox\" name=\"number_filter\" id=\"number_filter\"/><label class=\"switch\" for=\"number_filter\"><span class=\"ball\"></span></label>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label for=\"string_filter\">去掉纯字母</label><input type=\"checkbox\" name=\"string_filter\" id=\"string_filter\"/><label class=\"switch\" for=\"string_filter\"><span class=\"ball\"></span></label>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label>长度</label>\n" +
            "    <label for=\"short\" style=\"width: 80px;padding-right: 5px;\">不能小于</label><input type=\"number\" name=\"short\" id=\"short\" style=\"width: 100px;\" placeholder=\"请输入最小长度, 0为忽略\" title=\"请输入最小长度, 0为忽略\" value=\"6\" />\n" +
            "    <label for=\"long\" style=\"width: 60px;padding-right: 5px;\">不能大于</label><input type=\"number\" name=\"long\" id=\"long\" style=\"width: 100px;\" placeholder=\"请输入最大长度, 0为忽略\" title=\"请输入最大长度, 0为忽略\" value=\"16\" />\n" +
            "</div>\n" +
            "<div>\n" +
            "    <label>大小写</label>\n" +
            "    <label for=\"capitalize\" style=\"width: 80px;padding-right: 5px;\">首字母大写</label><input type=\"checkbox\" checked=\"checked\" name=\"capitalize\" id=\"capitalize\" value=\"首字母大写\"/><label class=\"switch\" for=\"capitalize\"><span class=\"ball\"></span></label>\n" +
            "    <label for=\"lowercase\" style=\"width: 50px;padding-right: 5px;\">全小写</label><input type=\"checkbox\" name=\"lowercase\" id=\"lowercase\" value=\"全小写\"/><label class=\"switch\" for=\"lowercase\"><span class=\"ball\"></span></label>\n" +
            "    <label for=\"uppercase\" style=\"width: 50px;padding-right: 5px;\">全大写</label><input type=\"checkbox\" name=\"uppercase\" id=\"uppercase\" value=\"全大写\"/><label class=\"switch\" for=\"uppercase\"><span class=\"ball\"></span></label>\n" +
            "</div>\n" +
            "<div style=\"margin: 20px 0 0 50px;\">\n" +
            "    <label for=\"generate\"></label><input type=\"button\" name=\"generate\" id=\"generate\" value=\"生成密码\" onclick=\"main()\" />\n" +
            "    <label for=\"reset\"></label><input type=\"button\" name=\"reset\" id=\"reset\" value=\"重置\" onclick=\"reset()\"/>\n" +
            "    <label for=\"download\"></label><input type=\"button\" name=\"download\" id=\"download\" value=\"下载\" onclick=\"download()\"/>\n" +
            "</div>\n" +
            "<div style=\"margin-top: 50px;display: none;\" id=\"result\">\n" +
            "    <div style=\"width: 350px;text-align: center;\">\n" +
            "        <div style=\"margin-bottom: 10px;\">\n" +
            "            <span>单项信息密码-<span id=\"pass_first_length\"></span>个</span>\n" +
            "            <input type=\"button\" name=\"pass_first_copy\" id=\"pass_first_copy\" value=\"复制\" style=\"width: 50px;height: 20px;\" onclick=\"copy('pass_first_text')\" />\n" +
            "        </div>\n" +
            "        <label for=\"pass_first_text\" style=\"display: none\"></label><textarea id=\"pass_first_text\" cols=\"25\"></textarea>\n" +
            "    </div>\n" +
            "    <div style=\"width: 350px;text-align: center;\">\n" +
            "        <div style=\"margin-bottom: 10px;\">\n" +
            "            <span>两项信息密码-<span id=\"pass_second_length\"></span>个</span>\n" +
            "            <input type=\"button\" name=\"pass_second_copy\" id=\"pass_second_copy\" value=\"复制\" style=\"width: 50px;height: 20px;\" onclick=\"copy('pass_second_text')\" />\n" +
            "        </div>\n" +
            "        <label for=\"pass_second_text\" style=\"display: none\"></label><textarea id=\"pass_second_text\" cols=\"25\"></textarea>\n" +
            "    </div>\n" +
            "    <div style=\"width: 350px;text-align: center;\">\n" +
            "        <div style=\"margin-bottom: 10px;\">\n" +
            "            <span>两项信息加连接符密码-<span id=\"pass_third_length\"></span>个</span>\n" +
            "            <input type=\"button\" name=\"pass_third_copy\" id=\"pass_third_copy\" value=\"复制\" style=\"width: 50px;height: 20px;\" onclick=\"copy('pass_third_text')\" />\n" +
            "        </div>\n" +
            "        <label for=\"pass_third_text\" style=\"display: none\"></label><textarea id=\"pass_third_text\" cols=\"25\"></textarea>\n" +
            "    </div>\n" +
            "    <div style=\"width: 350px;text-align: center;\">\n" +
            "        <div style=\"margin-bottom: 10px;\">\n" +
            "            <span>全部密码-<span id=\"pass_all_length\"></span>个</span>\n" +
            "            <input type=\"button\" name=\"pass_all_copy\" id=\"pass_all_copy\" value=\"复制\" style=\"width: 50px;height: 20px;\" onclick=\"copy('pass_all_text')\" />\n" +
            "        </div>\n" +
            "        <label for=\"pass_all_text\" style=\"display: none\"></label><textarea id=\"pass_all_text\" cols=\"25\"></textarea>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div id=\"loading\" style=\"display: none;\">\n" +
            "    <div class=\"loading\">\n" +
            "        <div class=\"loading-bg\"></div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div id=\"notification\" style=\"position: fixed;right: -20px;top: 20px;visibility: hidden;opacity: 0;width: 160px; height: 60px; background-color: white; text-align: center;line-height: 60px;border: 1px solid rgb(229,230,235);border-radius: 4px;box-shadow: 0 4px 10px #0000001a;transition: right 0.4s ease-out, opacity 0.2s ease-in, visibility 0.2s ease-in;\"><svg class=\"alert-icon\" viewBox=\"0 0 48 48\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" stroke=\"currentColor\" stroke-width=\"4\" stroke-linecap=\"butt\" stroke-linejoin=\"miter\"><path fill-rule=\"evenodd\" clip-rule=\"evenodd\" d=\"M24 44c11.046 0 20-8.954 20-20S35.046 4 24 4 4 12.954 4 24s8.954 20 20 20Zm2-30a1 1 0 0 0-1-1h-2a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1v-2Zm0 17h1a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-6a1 1 0 0 1-1-1v-2a1 1 0 0 1 1-1h1v-8a1 1 0 0 1-1-1v-2a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v11Z\" fill=\"currentColor\" stroke=\"none\"></path></svg>已复制</div>\n" +
            "</body>\n" +
            "</html>";

    public String getHtmlContent(){
        return this.HTML_CONTENT;
    }

}
