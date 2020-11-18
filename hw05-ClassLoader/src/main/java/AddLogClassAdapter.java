import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class AddLogClassAdapter extends ClassVisitor {
    public AddLogClassAdapter(ClassWriter classWriter) {
        super();
    }
}
