#!/usr/bin/python3
# -*- coding:utf-8 -*-
import requests,sys
requests.urllib3.disable_warnings()


proxies = {
    'http': 'http://127.0.0.1:7890', 
    'https': 'http://127.0.0.1:7890'
    }

def main(url):
    headers = {'Connection': 'keep-alive',
    'Cache-Control': 'max-age=0',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozlila/5.0 (Linux; Android 7.0; SM-G892A Bulid/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Moblie Safari/537.36',
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
    'Accept-Encoding': 'gzip, deflate',
    'Accept-Language': 'en-US,en;q=0.9,fr;q=0.8',
    'referer': 'www.google.com'}
    req = requests.get(url,timeout=20,headers=headers,verify=False)
    if req.status_code == 200:
        print("存活")
    else:
        print(str(req.status_code)+"失败")

main(sys.argv[1])