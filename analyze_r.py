import os
import struct

def read_int(data, offset):
    return struct.unpack('>I', data[offset:offset+4])[0]

def analyze_r_file(filepath):
    print(f"Analyzing {filepath}...")
    with open(filepath, 'rb') as f:
        data = f.read()
    
    # Header analysis based on Toolkit.java
    # getResourceInt(4) is count.
    # getResourceInt starts reading at offset.
    
    if len(data) < 8:
        print("File too short")
        return

    # First 4 bytes might be ignored or magic? Toolkit.java reads offset 4 first for count.
    magic = read_int(data, 0)
    count = read_int(data, 4)
    print(f"Magic (0-3): {hex(magic)}")
    print(f"Count (4-7): {count}")
    
    print("--- Items ---")
    for i in range(min(count, 10)): # Print first 10
        offset_loc = 8 + (i * 4)
        if offset_loc + 4 > len(data):
            print("Offset table out of bounds")
            break
            
        item_offset = read_int(data, offset_loc)
        
        # Calculate size
        if i == count - 1:
            next_offset = len(data) # Just guess for last item? Toolkit uses total length
            # Toolkit: i == resourceInt - 1 ? length - resourceInt2 : getResourceInt(((i * 4) + 8) + 4) - resourceInt2
        else:
            next_offset_loc = offset_loc + 4
            next_offset = read_int(data, next_offset_loc)
            
        size = next_offset - item_offset
        
        # Peek at data
        header_bytes = data[item_offset:item_offset+8].hex() if item_offset + 8 <= len(data) else "EOF"
        
        # Check for PNG signature
        is_png = data[item_offset:item_offset+8].startswith(b'\x89PNG\r\n\x1a\n')
        type_str = "PNG" if is_png else "Unknown"
        
        print(f"Index {i}: Offset {item_offset}, Size {size}, Header: {header_bytes} ({type_str})")

# Test with r14
target_file = r'c:\Users\amiku\Downloads\JavaClassicGame\OriginalDecompiledGame\resources\assets\r14'
if os.path.exists(target_file):
    analyze_r_file(target_file)
else:
    print(f"File not found: {target_file}")
