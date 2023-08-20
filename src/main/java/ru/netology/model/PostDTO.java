package ru.netology.model;

public class PostDTO {
  private long id;
  private String content;
  private boolean isRemove;

  public PostDTO() {
  }

  public PostDTO(long id, String content, boolean isRemove) {
    this.id = id;
    this.content = content;
    this.isRemove = isRemove;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isRemove() {
    return isRemove;
  }

  public void setRemove(boolean remove) {
    isRemove = remove;
  }

  @Override
  public String toString() {
    return "PostDTO{" +
            "id=" + id +
            ", content='" + content + '\'' +
            ", isRemove=" + isRemove +
            '}';
  }
}
