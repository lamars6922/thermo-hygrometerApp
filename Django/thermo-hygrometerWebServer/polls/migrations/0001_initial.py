# -*- coding: utf-8 -*-
# Generated by Django 1.11.17 on 2018-12-17 10:36
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='humtem',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=200)),
                ('humi', models.CharField(max_length=200)),
                ('temp', models.CharField(max_length=200)),
            ],
        ),
    ]
