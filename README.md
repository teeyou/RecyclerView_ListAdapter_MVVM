# 성능이 개선된 RecyclerView
- ListAdapter, DiffUtil.ItemCallback을 이용해서 구현하면 변화가 생긴 아이템만 재구성함
- RecyclerView.adapter = myAdapter(list) 대신에, myAdapter.submitList(list) 를 써서 UI변경시킴
- MutableLiveData를 써야 setter를 사용해서 관찰하고 있는 value 변경 가능
- setValue()는 메인스레드에서 값 변경
- postValue()는 백그라운드에서 값 변경
- dataBinding 할 때 xml의 변수와 클래스 안에 있는 변수를 binding하고 사용하는거 잊으면 안됨
- ConstraintLayout과 같은 레이아웃으로 감싸고 있지않으면 자동생성되는 Binding class가 생성되지 않음

## MyAdapter 클래스 안에
- Adapter, Holder, MyDiffCallback, MyItemTouchHelperCallback 구현
- list item 위 아래로 드래그해서 위치 변경
- list item 왼쪽으로 끌어서 삭제

## 
- Fab버튼을 누르면 list를 reverse시킴
- list_item의 ConstraintLayout에 onClick()을 이용해서 item 터치시 snackBar 생성되도록 함
