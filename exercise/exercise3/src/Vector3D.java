public class Vector3D {
  private final double x;
  private final double y;
  private final double z;
  
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }
  public double getZ() {
    return this.z;
  }
  
  @Override
  public String toString() {
    return String.format("(%.2f, %.2f, %.2f)", x, y, z);
  }
  public double getMagnitude() {
    return Math.sqrt(x*x + y*y + z*z);
  }
  public Vector3D normalize() throws IllegalArgumentException { 
    double mag = this.getMagnitude();
    if (mag < 0) {
      throw new IllegalArgumentException("cannot normalize");
    }
    return new Vector3D(x/mag, y/mag, z/mag);
  }
}
