void main() {
  List<int> li = [52, 21, 10, 55, 2, 8, 66,5];
  print("\nLength of the list: ${li.length}");

  print("Original List:");
  for (int i = 0; i < li.length; i++) {
    print(li[i]);
  }
  li.remove(55);
  li.remove(21);

  print("\nUpdated List after removing 55 and 21:");
  for (int i = 0; i < li.length; i++) {
    print(li[i]);
  }
}

