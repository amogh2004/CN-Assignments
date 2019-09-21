import requests
from requests.auth import HTTPBasicAuth
page = requests.get('http://202.62.79.36/~rprustagi/auth/auth.html', auth=HTTPBasicAuth('dd', 'sss'))

print(page.status_code)
