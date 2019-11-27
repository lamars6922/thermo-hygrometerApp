# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from sense_hat import SenseHat
from django.shortcuts import render
from django.http import JsonResponse
from .models import *
import datetime, time

# Create your views here.
def index(request):
    sense = SenseHat()
    new_temp = sense.get_temperature()
    new_humidity = sense.get_humidity()
    now = datetime.datetime.now()
    new_date = now.strftime('%Y-%m-%d %H:%M:%S')
    
        
    return JsonResponse({'humidity':new_humidity, 'temp':new_temp, 'date': new_date}, json_dumps_params = {'ensure_ascii':True})

def AutoSave(request):
    sense = SenseHat()
    new_temp = sense.get_temperature()
    new_humidity = sense.get_humidity()
    now = datetime.datetime.now()
    new_date = now.strftime('%Y-%m-%d %H:%M:%S')
    
    new_HT = humtem(date = new_date, humi = new_humidity, temp = new_temp)
    new_HT.save()
    
    return render(request, 'polls/AutoSave.html')

def LoadData(request):
    history = humtem.objects.all()
    dataList = []
    for i in range(len(history)):
        data = []
        data.append(history[i].date)
        data.append(history[i].temp)
        data.append(history[i].humi)
        dataList.append(data)
    
        
    return JsonResponse({'history':dataList}, json_dumps_params = {'ensure_ascii':True})
    