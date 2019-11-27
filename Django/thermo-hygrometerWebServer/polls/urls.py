from django.conf.urls import url
from . import views

app_name = 'polls'
urlpatterns = [
        url(r'^$', views.index, name = 'index'),
        url(r'^autosave/', views.AutoSave, name = 'AutoSave'),
        url(r'^loaddata/', views.LoadData, name = 'LoadData'),
    ]