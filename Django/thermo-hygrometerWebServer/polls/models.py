# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.
class humtem(models.Model):
    date = models.CharField(max_length = 200)
    humi = models.CharField(max_length = 200)
    temp = models.CharField(max_length = 200)
    
    def __str__(self):
        return self.date
    